package bank;

import currency.Currency;
import exceptions.bank.NotEnoughMoneyException;

import java.util.HashMap;
import java.util.Map;

/**
 * Bank Account
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class BankAccount {

    /**
     * The money available on this account
     */
    private double money;

    /**
     * The main currency for this account
     */
    private Currency currency;

    /**
     * Constructor
     */
    public BankAccount() {
        money = 500;
        currency = Currency.EUR;
    }

    /**
     * Deduce money from the account
     * @param amount the money that will be deduced
     * @return true if there is enough money to do so
     * @throws NotEnoughMoneyException if there isn't enough money
     */
    public boolean deduce(double amount) throws NotEnoughMoneyException {
        if (money >= amount) {
            money -= amount;
            return true;
        }
        throw new NotEnoughMoneyException();
    }

    /**
     * Little GIFT : increase your money
     */
    public void glitch() {
        money += 500.00;
    }

    /**
     * Returns the currency of the account
     * @return the currency of the account
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Returns the money on the account
     * @return the money on the account
     */
    public double getAmount() {
        return money;
    }

}
