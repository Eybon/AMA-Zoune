package bank;

import bank.generated.CurrencyConvertorStub;
import exceptions.bank.BankAccountNotFoundException;
import exceptions.bank.CreditCardNotFoundException;
import exceptions.bank.CurrencyChangeRateErrorException;
import exceptions.bank.NotEnoughMoneyException;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Bank
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Bank {


    /**
     * Remote endpoint containing currency convertor
     */
    private final static String providerEndpoint = "http://www.webservicex.net/CurrencyConvertor.asmx";

    /**
     * Stub used for service consumption
     */
    private CurrencyConvertorStub currencyStub;

    /**
     * List of credit cards
     */
    private Map<String, CreditCard> creditCards;

    /**
     * List of bank accounts
     */
    private Map<String, BankAccount> bankAccounts;

    public static void main(String[] args) {

    }

    /**
     * Constructor
     * @throws AxisFault exception
     */
    public Bank() throws AxisFault {
        creditCards = new HashMap<String, CreditCard>();
        creditCards.put("123412341234123412", new CreditCard("123412341234123412", "M BERGEON MAXIME", 01, 16, "111"));

        bankAccounts = new HashMap<String, BankAccount>();
        bankAccounts.put("123412341234123412", new BankAccount());

        currencyStub = new CurrencyConvertorStub(providerEndpoint);
    }

    /**
     * Returns the money on a bank account (test purpose)
     * @param creditcard credit card number related to the account
     * @return the money on the bank account linked to the creditcard
     */
    public double getStateBankAccount(String creditcard) throws BankAccountNotFoundException {
        BankAccount ba = bankAccounts.get(creditcard);
        if (ba == null) {
            throw new BankAccountNotFoundException();
        }
        return ba.getAmount();
    }

    /**
     * Returns the change rate between two currencies
     * @param from the currency at the beginning
     * @param to the currency at the ending
     * @return the change rate between two currencies
     */
    public double getCurrencyChangeRate(String from, String to) throws RemoteException {

        CurrencyConvertorStub.Currency fromCurrency = CurrencyConvertorStub.Currency.Factory.fromString(from, "");
        CurrencyConvertorStub.Currency toCurrency = CurrencyConvertorStub.Currency.Factory.fromString(to, "");

        CurrencyConvertorStub.ConversionRate rate = new CurrencyConvertorStub.ConversionRate();
        rate.setToCurrency(toCurrency);
        rate.setFromCurrency(fromCurrency);

        double result = this.currencyStub.conversionRate(rate).getConversionRateResult();
        return result;
    }

    /**
     * Check if a credit card is valid or not
     * @param creditcard the number of the credit card to use
     * @return true if the credit card does exist
     * @throws CreditCardNotFoundException if the credit card does not exist
     */
    public boolean checkCreditCard(String creditcard) throws CreditCardNotFoundException {
        CreditCard card = creditCards.get(creditcard);
        if (card == null) {
            throw new CreditCardNotFoundException();
        }
        return true;
    }

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
    public boolean doTransaction(String creditcard, String currency, int amount) throws BankAccountNotFoundException, RemoteException, NotEnoughMoneyException, CurrencyChangeRateErrorException {

        BankAccount bankAccount = bankAccounts.get(creditcard);
        if (bankAccount == null) {
            throw new BankAccountNotFoundException();
        }

        CurrencyConvertorStub.Currency fromCurrency = CurrencyConvertorStub.Currency.Factory.fromString(bankAccount.getCurrency().getCurrency(), "");
        CurrencyConvertorStub.Currency toCurrency = CurrencyConvertorStub.Currency.Factory.fromString(currency, "");

        CurrencyConvertorStub.ConversionRate rate = new CurrencyConvertorStub.ConversionRate();
        rate.setToCurrency(toCurrency);
        rate.setFromCurrency(fromCurrency);

        double result = this.currencyStub.conversionRate(rate).getConversionRateResult();
        if (result < 0) {
            throw new CurrencyChangeRateErrorException();
        }
        bankAccount.deduce(result * amount);
        bankAccounts.put(creditcard, bankAccount);

        return true;
    }

}