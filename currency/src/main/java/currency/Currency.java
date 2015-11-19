package currency;

/**
 * Created by Maxime on 19/11/2015.
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
