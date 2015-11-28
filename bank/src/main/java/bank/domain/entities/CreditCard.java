package bank.domain.entities;

/**
 * CreditCard
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class CreditCard {

    /**
     * The number of the credit card
     */
    private String num;

    /**
     * The owner of the credit card
     */
    private String owner;

    /**
     * The month of the limit date
     */
    private int month;

    /**
     * The year of the limit date
     */
    private int year;

    /**
     * The secret sentence on the back of the card
     */
    private String ccv;

    /**
     * Constructor
     * @param num The number of the credit card
     * @param owner The owner of the credit card
     * @param month The month of the limit date
     * @param year The year of the limit date
     * @param ccv The secret sentence on the back of the card
     */
    public CreditCard(String num, String owner, int month, int year, String ccv) {

        this.num = num;
        this.owner = owner;
        this.month = month;
        this.year = year;
        this.ccv = ccv;

    }

    public boolean doesVerify(String owner, int limitmonth, int limityear, String CCV) {
        return (owner.equals(this.owner) && CCV.equals(this.ccv) && (limitmonth == this.month) && (limityear == this.year));
    }

}
