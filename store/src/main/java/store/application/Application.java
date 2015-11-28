package store.application;


import org.apache.axis2.AxisFault;
import store.api.services.IDomainService;
import store.domain.entities.Basket;
import store.domain.entities.BasketItem;
import store.domain.entities.Order;
import store.domain.services.impl.Service;
import store.domain.valueobjects.impl.Data;
import store.exceptions.AccountNotFoundException;
import store.exceptions.EmptyBasketException;
import store.exceptions.NoOrderKnownException;
import store.generated.bank.*;
import store.generated.provider.ProviderProductNotFoundExceptionException;
import store.generated.provider.ProviderSoldOutExceptionException;
import store.generated.provider.ProviderStub;
import store.infrastructure.factories.ETypeDataFactory;
import store.infrastructure.factories.StoreDataFactory;
import store.infrastructure.repositories.EventStoreRepository;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Set;

/**
 * Application
 * Format of the pieces of data we'll have to persist
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Application implements IDomainService {

    private IDomainService domainService;

    private EventStoreRepository eventStoreRepository;

    public Application() throws RemoteException, ProviderProductNotFoundExceptionException {
        this.domainService = new Service();
        this.eventStoreRepository =  new EventStoreRepository();
    }

    @Override
    public Integer login(String email, String password) throws AccountNotFoundException {
        return this.domainService.login(email, password);
    }

    @Override
    public ProviderStub.Product[] getListProducts() throws RemoteException {
        return this.domainService.getListProducts();
    }

    @Override
    public ProviderStub.Product informationProduct(String identifier) throws RemoteException, ProviderProductNotFoundExceptionException {
        return this.domainService.informationProduct(identifier);
    }

    @Override
    public BasketItem[] detailsBasket(int user) throws RemoteException {
        return this.domainService.detailsBasket(user);
    }

    @Override
    public boolean addProductToBasket(int user, String product, int amount) throws RemoteException, ProviderProductNotFoundExceptionException, ProviderSoldOutExceptionException {

        /*Data data = new StoreDataFactory().getData(ETypeDataFactory.ADD_TO_CART.toString(), product, amount);
        eventStoreRepository.sendEvent(data);*/

        return this.domainService.addProductToBasket(user, product, amount);
    }

    @Override
    public Order validateBasket(int user) throws EmptyBasketException, ProviderProductNotFoundExceptionException, RemoteException, ProviderSoldOutExceptionException {
        return this.domainService.validateBasket(user);
    }

    @Override
    public Order detailsOrder(int user) throws NoOrderKnownException {
        return this.domainService.detailsOrder(user);
    }

    @Override
    public boolean payOrder(int user, String ccnumber, int limitmonth, int limityear, String owner, String CCV) throws RemoteException, BankCreditCardNotFoundExceptionException, BankWrongCreditCardDetailsExceptionException, BankRemoteExceptionException, BankNotEnoughMoneyExceptionException, BankBankAccountNotFoundExceptionException, BankCurrencyChangeRateErrorExceptionException, NoOrderKnownException {

        Order order = this.domainService.detailsOrder(user);
        boolean result = this.domainService.payOrder(user, ccnumber, limitmonth, limityear, owner, CCV);
        if (result) {

            Map<String, BasketItem> details = order.getProducts();
            Set<String> products = details.keySet();
            for (String product : products) {
                /*Data data = new StoreDataFactory().getData(ETypeDataFactory.PROCESS_ORDER.toString(), product, details.get(product).getQuantity());
                eventStoreRepository.sendEvent(data);*/
            }

        }

        return result;
    }
}
