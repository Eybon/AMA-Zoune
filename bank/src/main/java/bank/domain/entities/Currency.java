package bank.domain.entities;

/**
 * Currency
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public enum Currency {
    USD ("USD"),
    EUR ("EUR");

    private String currency;

    Currency (String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

}
