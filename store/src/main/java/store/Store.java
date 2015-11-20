package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import store.exceptions.AccountNotFoundException;
import store.exceptions.EmptyBasketException;
import store.generated.bank.*;
import store.generated.provider.ProviderProductNotFoundExceptionException;
import org.apache.axis2.AxisFault;
import store.generated.provider.ProviderSoldOutExceptionException;
import store.generated.provider.ProviderStub;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The Store is aimed to act as an interface between the client and the provider.
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Store 
{

    /**
     * Remote endpoint containing provider resources
     * TODO : Change it according to your SOAP API
     */
    private final static String providerEndpoint = "http://192.168.0.12:9763/services/Provider/";

    /**
     * Remote endpoint containing bank resources
     * TODO : Change it according to your SOAP API
     */
    private final static String bankEndpoint = "http://192.168.0.12:9763/services/Bank/";

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

        orders = new HashMap<Integer, Order>();
    }

    public static void main (String[] args) {

    }

    public Store () throws AxisFault {
        this.provider = new ProviderStub(providerEndpoint);
        this.bank = new BankStub(bankEndpoint);

        currency = "USD";
    }

	/**
	 * Set up a session (or not) for the given credentials
	 * @param email the email address we use to login
	 * @param password the given password
	 * @return a json sentence noticing success or failure
	 */
	public Integer login(String email, String password) throws AccountNotFoundException {
		if (accounts.get(email) != null && accounts.get(email).getPassword().equals(password)) {
            return accounts.get(email).getId();
        }
        throw new AccountNotFoundException();
	}

	/**
	 * Returns informations about a given product
	 * @param identifier the identifier of the product
	 * @return a json string containing the pieces of informations
	 */
	public ProviderStub.Product informationProduct(String identifier) throws RemoteException, ProviderProductNotFoundExceptionException {
        ProviderStub.GetProduct product = new ProviderStub.GetProduct();
        product.setIdentifier(identifier);

        return this.provider.getProduct(product).get_return();
	}
	
	/**
	 * Return the availability of a given product
	 * @param identifier the identifier of the product
	 * @return the available amount for this product
	 */
	public int availabilityProduct(String identifier) {
		//TODO
		return 0;
	}
	
	/**
	 * Returns the details of the basket for the given user
	 * @param user the identifier of the user we need the basket for
	 * @return a json string containing every detail
	 */
	public String detailsBasket(int user) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(baskets.get(user));
		return json;

	}
	
	/**
	 * Add a product to the basket of the given user
	 * @param user the user who add the product to his basket
	 * @param product the product we want to add to the basket
	 * @param amount the amount of the given product we want
	 * @return true if the products could have been added, false otherwise
	 */
	public boolean addProductToBasket(int user, String product, int amount) throws RemoteException, ProviderProductNotFoundExceptionException, ProviderSoldOutExceptionException {
        ProviderStub.Product prod = informationProduct(product);
        if (prod.getQuantity() >= amount) {
            Basket basket = baskets.get(user);
            if (basket == null) {
                baskets.put(user, new Basket());
            }
            baskets.get(user).set(product, amount);
            return true;
        }
		throw new ProviderSoldOutExceptionException();
	}
	
	/**
	 * Validate the basket for the given user
	 * @param user the identifier of the user we want to validate the basket for
	 * @return a json string containing details about the order 
	 */
	public String validateBasket(int user) throws EmptyBasketException, ProviderProductNotFoundExceptionException, RemoteException, ProviderSoldOutExceptionException {

        //First of all, if there is already an order for this user which is not paid, just cancel it
        orders.remove(user);

        Basket basket = baskets.get(user);
        if (basket == null) {
            throw new EmptyBasketException();
        }

        Map<String, Integer> basketContent = basket.getProducts();
        Set<String> products = basketContent.keySet();
        double cost = 0.0;

        //Try to check every single product
        for (String product : products) {
            ProviderStub.Product prod = informationProduct(product);
            if (prod.getQuantity() < basketContent.get(product)) {
                throw new ProviderSoldOutExceptionException();
            }

            //This may not be the best solution, and yes this is tricky
            ProviderStub.OrderProduct order = new ProviderStub.OrderProduct();
            order.setIdentifier(product);
            order.setAmount(basketContent.get(product));
            this.provider.orderProduct(order).get_return();

            cost += prod.getPrice() * 1.5 * basketContent.get(product);
        }

        //Create an order containing our actual basket
        Order retour = new Order(basketContent,cost);
        orders.put(user, retour);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
		return gson.toJson(retour);
	}
	
	/**
	 * Ask payment for the basket of the current user
	 * @param user the identifier of the user
	 * @param ccnumber the credit card number
	 * @param limitmonth the limit date written on the card (month)
     * @param limityear the limit date written on the card (year)
	 * @param owner the owner of the credit card
	 * @param CCV the 3 secret symbols on the card
	 * @return true if the order has been made successfuly, false otherwise
	 */
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
	
};