package store.domain.entities;

/**
 * User Account
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class UserAccount {

    private static int identifier = 1;

    /**
     * Identifier of the account
     */
    private int id;

    /**
     * Email used for the account
     */
    private String email;

    /**
     * Password used for the account
     */
    private String password;

    /**
     * Constructor
     * @param email Email used for the account
     * @param password Password used for the account
     */
    public UserAccount(String email, String password) {
        this.id = identifier++;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns the identifier of the account
     * @return the identifier of the account
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the email linked to the account
     * @return the email linked to the account
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password linked to the account
     * @return the password linked to the account
     */
    public String getPassword() {
        return password;
    }
}