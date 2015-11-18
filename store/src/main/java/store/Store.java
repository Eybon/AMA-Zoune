package store;

import java.sql.Date;

/**
 * The Store is aimed to act as an interface between the client and the provider.
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Store 
{
	
	/**
	 * Set up a session (or not) for the given credentials
	 * @param email the email address we use to login
	 * @param password the given password
	 * @return a json sentence noticing success or failure
	 */
	public String login(String email, String password) {
		//TODO
		return null;
	}

	/**
	 * Returns informations about a given product
	 * @param identifier the identifier of the product
	 * @return a json string containing the pieces of informations
	 */
	public String informationProduct(String identifier) {
		//TODO
		return null;
	}
	
	/**
	 * Return the availability of a given product
	 * @param identifier the identifier of the product
	 * @return the available amount for this product
	 */
	public int availabilityProduct(String identifier) {
		//TODO
		return 0;
	}
	
	/**
	 * Returns the details of the basket for the given user
	 * @param user the identifier of the user we need the basket for
	 * @return a json string containing every detail
	 */
	public String detailsBasket(int user) {
		//TODO
		return null;
	}
	
	/**
	 * Add a product to the basket of the given user
	 * @param user the user who add the product to his basket
	 * @param product the product we want to add to the basket
	 * @param amount the amount of the given product we want
	 * @return true if the products could have been added, false otherwise
	 */
	public boolean addProductToBasket(int user, String product, int amount) {
		//TODO
		return false;
	}
	
	/**
	 * Validate the basket for the given user
	 * @param user the identifier of the user we want to validate the basket for
	 * @return a json string containing details about the order 
	 */
	public String validateBasket(int user) {
		//TODO
		return null;
	}
	
	/**
	 * Ask payment for the basket of the current user
	 * @param user the identifier of the user
	 * @param ccnumber the credit card number
	 * @param limit the limit date written on the card
	 * @param owner the owner of the credit card
	 * @param CCV the 3 secret symbols on the card
	 * @return true if the order has been made successfuly, false otherwise
	 */
	public boolean payOrder(int user, String ccnumber, Date limit, String owner, int CCV) {
		//TODO
		return false;
	}
	
};