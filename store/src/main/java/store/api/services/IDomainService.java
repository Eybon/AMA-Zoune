package store.api.services;

import store.domain.entities.BasketItem;
import store.domain.entities.Order;
import store.exceptions.AccountNotFoundException;
import store.exceptions.EmptyBasketException;
import store.exceptions.NoOrderKnownException;
import store.generated.bank.*;
import store.generated.provider.ProviderProductNotFoundExceptionException;
import store.generated.provider.ProviderSoldOutExceptionException;
import store.generated.provider.ProviderStub;

import java.rmi.RemoteException;

/**
 * IDomainService
 * Interface describing what a store service should implement
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface IDomainService {

    /**
     * Return the token corresponding to an user account
     * @param email the email of the account
     * @param password the password of the account
     * @return the token corresponding to an user account (if it exists)
     * @throws AccountNotFoundException exception thrown if the credentials are wrong or if the user does not exist
     */
    public Integer login(String email, String password) throws AccountNotFoundException;

    /**
     * Returns the list of products available from the store
     * @return the list of products
     * @throws RemoteException
     */
    public ProviderStub.Product[] getListProducts() throws RemoteException;

    /**
     * Return a product corresponding to the given identifier
     * @param identifier the name of a product
     * @return the product if it exists
     * @throws RemoteException
     * @throws ProviderProductNotFoundExceptionException if the product does not exist
     */
    public ProviderStub.Product informationProduct(String identifier) throws RemoteException, ProviderProductNotFoundExceptionException;

    /**
     * Returns the list of products which are in the basket
     * @param user the user token
     * @return the list of products which are in the basket
     * @throws RemoteException
     */
    public BasketItem[] detailsBasket(int user) throws RemoteException;

    /**
     * Add a product to the basket of an user
     * @param user the token of the user
     * @param product the name of the product to add to the basket
     * @param amount the amount to add
     * @return true if all has been done successfully
     * @throws RemoteException
     * @throws ProviderProductNotFoundExceptionException if the product does not exist
     * @throws ProviderSoldOutExceptionException if the product is out of stock from the provider
     */
    public boolean addProductToBasket(int user, String product, int amount) throws RemoteException, ProviderProductNotFoundExceptionException, ProviderSoldOutExceptionException;

    /**
     * Validate the basket of an user
     * @param user the token of an user
     * @return an instance of object Order
     * @throws EmptyBasketException if the basket is empty
     * @throws ProviderProductNotFoundExceptionException if the product does not exist
     * @throws RemoteException
     * @throws ProviderSoldOutExceptionException if the product is out of stock
     */
    public Order validateBasket(int user) throws EmptyBasketException, ProviderProductNotFoundExceptionException, RemoteException, ProviderSoldOutExceptionException;

    /**
     * Return the details of an order or an user
     * @param user token of an user
     * @return the details of an order or an user
     * @throws NoOrderKnownException if no order is known for this user
     */
    public Order detailsOrder(int user) throws NoOrderKnownException;

    /**
     * Pay an order
     * @param user the token of an user
     * @param ccnumber the credit card number of the bank account to use
     * @param limitmonth the limit month of the credit card of the bank account
     * @param limityear the limit year of the credit card of the bank account
     * @param owner the owner of the credit card of the bank account
     * @param CCV the secret token of the credit card of the bank account
     * @return true if the order has been paid successfully
     * @throws RemoteException
     * @throws BankCreditCardNotFoundExceptionException if the credit card does not exist
     * @throws BankWrongCreditCardDetailsExceptionException if the given pieces of information are wrong
     * @throws BankRemoteExceptionException
     * @throws BankNotEnoughMoneyExceptionException if the bank account linked to the credit card does not have enough money
     * @throws BankBankAccountNotFoundExceptionException if the bank account does not exist
     * @throws BankCurrencyChangeRateErrorExceptionException if the bank can not reach or encounter an exception while accessing the "currency change rate" service
     * @throws NoOrderKnownException if there is no order to pay for
     */
    public boolean payOrder(int user, String ccnumber, int limitmonth, int limityear, String owner, String CCV) throws RemoteException, BankCreditCardNotFoundExceptionException, BankWrongCreditCardDetailsExceptionException, BankRemoteExceptionException, BankNotEnoughMoneyExceptionException, BankBankAccountNotFoundExceptionException, BankCurrencyChangeRateErrorExceptionException, NoOrderKnownException;



}