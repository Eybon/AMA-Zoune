package store.domain.services.impl;


import org.apache.axis2.AxisFault;
import store.api.services.IDomainService;
import store.domain.entities.*;
import store.exceptions.AccountNotFoundException;
import store.exceptions.EmptyBasketException;
import store.exceptions.NoOrderKnownException;
import store.generated.bank.*;
import store.generated.provider.ProviderProductNotFoundExceptionException;
import store.generated.provider.ProviderSoldOutExceptionException;
import store.generated.provider.ProviderStub;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.rmi.RemoteException;
import java.util.*;

/**
 * Service
 * Implementation of the domain for a store system
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Service implements IDomainService {

    /**
     * Remote endpoint containing provider resources
     * TODO : Change it according to your SOAP API
     */
    private final static String providerEndpoint = "http://localhost:9763/services/Provider/";

    /**
     * Remote endpoint containing bank resources
     * TODO : Change it according to your SOAP API
     */
    private final static String bankEndpoint = "http://localhost:9763/services/Bank/";

    /**
     * Container for users accounts
     */
    private static Map<String, UserAccount> accounts;

    /**
     * Container for users baskets
     */
    private static Map<Integer, Basket> baskets;

    /**
     * Container for users orders
     */
    private static Map<Integer, Order> orders;

    /**
     * Default currency
     */
    private String currency;

    /**
     * Stub dedicated to communication with the provider
     */
    private ProviderStub provider;

    /**
     * Stub dedicated to communication with the bank
     */
    private BankStub bank;

    static {
        accounts = new HashMap<String, UserAccount>();
        accounts.put("maxime.bergeon@etu.univ-nantes.fr", new UserAccount("maxime.bergeon@etu.univ-nantes.fr", "test"));
        accounts.put("antoine.forgerou@etu.univ-nantes.fr", new UserAccount("antoine.forgerou@etu.univ-nantes.fr", "antoine"));

        baskets = new HashMap<Integer, Basket>();
        Basket b = new Basket();

        ProviderStub.Product p1 = new ProviderStub.Product();
        p1.setName("Bikini_1");
        p1.setDescription("");
        p1.setPrice(100.00);

        ProviderStub.Product p2 = new ProviderStub.Product();
        p2.setName("Hot_grill_2");
        p2.setDescription("");
        p2.setPrice(350.00);

        b.set(p1, 5);
        b.set(p2, 3);

        baskets.put(1, b);

        orders = new HashMap<Integer, Order>();
    }

    public Service () throws AxisFault, RemoteException, ProviderProductNotFoundExceptionException {
        this.provider = new ProviderStub(providerEndpoint);
        this.bank = new BankStub(bankEndpoint);

        currency = "USD";
    }

    @Override
    public Integer login(String email, String password) throws AccountNotFoundException {
        if (accounts.get(email) != null && accounts.get(email).getPassword().equals(password)) {
            return accounts.get(email).getId();
        }
        throw new AccountNotFoundException();
    }

    @Override
    public ProviderStub.Product[] getListProducts() throws RemoteException {
        ProviderStub.GetListProduct listProducts = new ProviderStub.GetListProduct();

        return this.provider.getListProduct(listProducts).get_return();
    }

    @Override
    public ProviderStub.Product informationProduct(String identifier) throws RemoteException, ProviderProductNotFoundExceptionException {
        ProviderStub.GetProduct product = new ProviderStub.GetProduct();
        product.setIdentifier(identifier);

        return this.provider.getProduct(product).get_return();
    }

    @Override
    public BasketItem[] detailsBasket(int user) throws RemoteException {

        Basket basket = baskets.get(user);
        if (basket == null) {
            basket = new Basket();
            baskets.put(user, basket);
        }
        return basket.getItems();
    }

    @Override
    public boolean addProductToBasket(int user, String product, int amount) throws RemoteException, ProviderProductNotFoundExceptionException, ProviderSoldOutExceptionException {
        ProviderStub.Product prod = informationProduct(product);
        if (prod.getQuantity() >= amount) {
            Basket basket = baskets.get(user);
            if (basket == null) {
                baskets.put(user, new Basket());
            }
            if (amount <= 0) {
                baskets.get(user).remove(product);
                return true;
            }
            baskets.get(user).set(prod, amount);
            return true;
        }
        throw new ProviderSoldOutExceptionException();
    }

    @Override
    public Order validateBasket(int user) throws EmptyBasketException, ProviderProductNotFoundExceptionException, RemoteException, ProviderSoldOutExceptionException {

        //First of all, if there is already an order for this user which is not paid, just cancel it
        orders.remove(user);

        Basket basket = baskets.get(user);
        if (basket == null) {
            throw new EmptyBasketException();
        }

        Map<String, BasketItem> basketContent = basket.getProducts();
        Set<String> products = basketContent.keySet();
        double cost = 0.0;

        //Try to check every single product
        for (String product : products) {
            ProviderStub.Product prod = informationProduct(product);

            //This may not be the best solution, and yes this is tricky
            ProviderStub.OrderProduct order = new ProviderStub.OrderProduct();
            order.setIdentifier(product);
            order.setAmount(basketContent.get(product).getQuantity());
            this.provider.orderProduct(order).get_return();

            cost += prod.getPrice() * 1.5 * basketContent.get(product).getQuantity();
        }

        //Create an order containing our actual basket
        Order retour = new Order(basketContent,cost);
        orders.put(user, retour);

        return retour;
    }

    @Override
    public Order detailsOrder(int user) throws NoOrderKnownException {
        Order order = orders.get(user);
        if (order == null) {
            throw new NoOrderKnownException();
        }
        return order;
    }

    @Override
    public boolean payOrder(int user, String ccnumber, int limitmonth, int limityear, String owner, String CCV) throws RemoteException, BankCreditCardNotFoundExceptionException, BankWrongCreditCardDetailsExceptionException, BankRemoteExceptionException, BankNotEnoughMoneyExceptionException, BankBankAccountNotFoundExceptionException, BankCurrencyChangeRateErrorExceptionException {

        //First check the credit card
        BankStub.CheckCreditCard checkCard = new BankStub.CheckCreditCard();
        checkCard.setCreditcard(ccnumber);
        checkCard.setOwner(owner);
        checkCard.setLimitmonth(limitmonth);
        checkCard.setLimityear(limityear);
        checkCard.setCCV(CCV);

        this.bank.checkCreditCard(checkCard).get_return();

        //If it's alright, try to call the bank for the transaction
        BankStub.DoTransaction transaction = new BankStub.DoTransaction();
        transaction.setCreditcard(ccnumber);
        transaction.setAmount(orders.get(user).getCost());
        transaction.setCurrency(currency);

        this.bank.doTransaction(transaction).get_return();

        //Once the move is complete and ok, remove the order and empty the basket
        orders.remove(user);
        baskets.remove(user);

        return true;
    }

}
