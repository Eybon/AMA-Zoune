package bank.domain.services.impl;

import bank.domain.entities.BankAccount;
import bank.domain.entities.CreditCard;
import bank.api.services.IDomainService;
import bank.exceptions.*;
import bank.generated.CurrencyConvertorStub;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * Service
 * An implementation of a bank service
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Service implements IDomainService {

    /**
     * Remote endpoint containing currency convertor
     */
    private final static String currencyEndpoint = "http://www.webservicex.net/CurrencyConvertor.asmx";

    /**
     * Stub used for service consumption
     */
    private CurrencyConvertorStub currencyStub;

    /**
     * List of credit cards
     */
    private static Map<String, CreditCard> creditCards;

    /**
     * List of bank accounts
     */
    private static Map<String, BankAccount> bankAccounts;

    /**
     * TEST PURPOSE : while waiting for a persistant storage
     */
    static {
        creditCards = new HashMap<String, CreditCard>();
        creditCards.put("123412341234123412", new CreditCard("123412341234123412", "M BERGEON MAXIME", 01, 2016, "111"));

        bankAccounts = new HashMap<String, BankAccount>();
        bankAccounts.put("123412341234123412", new BankAccount());
    }

    /**
     * Constructor
     * @throws AxisFault exception
     */
    public Service() throws AxisFault {

        currencyStub = new CurrencyConvertorStub(currencyEndpoint);
    }

    @Override
    public double getStateBankAccount(String creditcard) throws BankAccountNotFoundException {
        BankAccount ba = bankAccounts.get(creditcard);
        if (ba == null) {
            throw new BankAccountNotFoundException();
        }
        return ba.getAmount();
    }

    @Override
    public double getCurrencyChangeRate(String from, String to) throws RemoteException {
        CurrencyConvertorStub.Currency fromCurrency = CurrencyConvertorStub.Currency.Factory.fromString(from, "");
        CurrencyConvertorStub.Currency toCurrency = CurrencyConvertorStub.Currency.Factory.fromString(to, "");

        CurrencyConvertorStub.ConversionRate rate = new CurrencyConvertorStub.ConversionRate();
        rate.setToCurrency(toCurrency);
        rate.setFromCurrency(fromCurrency);

        double result = this.currencyStub.conversionRate(rate).getConversionRateResult();
        return result;
    }

    @Override
    public boolean checkCreditCard(String creditcard, int limitmonth, int limityear, String owner, String CCV) throws CreditCardNotFoundException, WrongCreditCardDetailsException {
        CreditCard card = creditCards.get(creditcard);
        if (card == null) {
            throw new CreditCardNotFoundException();
        }

        if (!card.doesVerify(owner, limitmonth, limityear, CCV)) {
            throw new WrongCreditCardDetailsException();
        }

        return true;
    }

    @Override
    public boolean doTransaction(String creditcard, String currency, double amount) throws BankAccountNotFoundException, RemoteException, NotEnoughMoneyException, CurrencyChangeRateErrorException {
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
