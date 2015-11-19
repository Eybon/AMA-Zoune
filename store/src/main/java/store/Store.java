package store;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import store.generated.provider.ProviderStub.IProduct;
import org.apache.axis2.AxisFault;
import store.generated.provider.ProviderStub;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

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
     * Remote endpoint containing provider resources
     * TODO : Change it according to your SOAP API
     */
    private final static String providerEndpoint = "http://192.168.0.12:9763/services/Provider/";

    /**
     * Container for users accounts
     */
    private Map<String, UserAccount> accounts;

    /**
     * Container for users baskets
     */
    private Map<Integer, Basket> baskets;

    /**
     * Stub dedicated to communication with the provider
     */
    private ProviderStub provider;

    public static void main (String[] args) {

    }

    public Store () throws AxisFault {
        this.provider = new ProviderStub(providerEndpoint);

        accounts = new HashMap<String, UserAccount>();
        accounts.put("maxime.bergeon@etu.univ-nantes.fr", new UserAccount("maxime.bergeon@etu.univ-nantes.fr", "test"));
        accounts.put("antoine.forgerou@etu.univ-nantes.fr", new UserAccount("antoine.forgerou@etu.univ-nantes.fr", "antoine"));

        baskets = new HashMap<Integer, Basket>();

    }

	/**
	 * Set up a session (or not) for the given credentials
	 * @param email the email address we use to login
	 * @param password the given password
	 * @return a json sentence noticing success or failure
	 */
	public Integer login(String email, String password) {
		return (accounts.get(email) != null && accounts.get(email).getPassword().equals(password)) ? accounts.get(email).getId() : null;
	}

	/**
	 * Returns informations about a given product
	 * @param identifier the identifier of the product
	 * @return a json string containing the pieces of informations
	 */
	public IProduct informationProduct(String identifier) throws RemoteException {
        ProviderStub.GetProduct product = new ProviderStub.GetProduct();
        product.setIdentifier(identifier);

        return this.provider.getProduct(product).get_return();
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

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(baskets.get(user));
		return json;

	}
	
	/**
	 * Add a product to the basket of the given user
	 * @param user the user who add the product to his basket
	 * @param product the product we want to add to the basket
	 * @param amount the amount of the given product we want
	 * @return true if the products could have been added, false otherwise
	 */
	public boolean addProductToBasket(int user, String product, int amount) throws RemoteException {
        IProduct prod = informationProduct(product);
        if (prod.getQuantity() >= amount) {
            Basket basket = baskets.get(user);
            if (basket == null) {
                baskets.put(user, new Basket());
            }
            baskets.get(user).set(product, amount);
            return true;
        }
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