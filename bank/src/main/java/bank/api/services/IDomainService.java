package bank.api.services;

import bank.exceptions.*;

import java.rmi.RemoteException;

/**
 * IDomainService
 * Interface to define all that domains should implement
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface IDomainService {

    /**
     * Returns the money on a bank account (test purpose)
     * @param creditcard credit card number related to the account
     * @return the money on the bank account linked to the creditcard
     */
    double getStateBankAccount(String creditcard) throws BankAccountNotFoundException;

    /**
     * Returns the change rate between two currencies
     * @param from the currency at the beginning
     * @param to the currency at the ending
     * @return the change rate between two currencies
     */
    double getCurrencyChangeRate(String from, String to) throws RemoteException;

    /**
     * Check if a credit card is valid or not
     * @param creditcard the number of the credit card to use
     * @param limitmonth the limit date written on the card (month)
     * @param limityear the limit date written on the card (year)
     * @param owner the owner of the credit card
     * @param CCV the 3 secret symbols on the card
     * @return true if the credit card does exist
     * @throws CreditCardNotFoundException if the credit card does not exist
     * @throws WrongCreditCardDetailsException if the details of the card are not those given
     */
    boolean checkCreditCard(String creditcard, int limitmonth, int limityear, String owner, String CCV) throws CreditCardNotFoundException, WrongCreditCardDetailsException;

    /**
     * Try to transfer money according to a payment
     * @param creditcard the number of the credit card used for the payment
     * @param currency the targeted currency
     * @param amount the amount of the payment (before currency being converted)
     * @return true if the transaction has been made successfuly
     * @throws BankAccountNotFoundException if the bank does not own an account for this credit card
     * @throws RemoteException exception for remote computation
     * @throws NotEnoughMoneyException if the bank account does not have enough money
     */
    boolean doTransaction(String creditcard, String currency, double amount) throws BankAccountNotFoundException, RemoteException, NotEnoughMoneyException, CurrencyChangeRateErrorException;

}