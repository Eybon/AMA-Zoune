package store;

import java.util.HashMap;
import java.util.Map;

/**
 * Basket
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public  class Basket {

    /**
     * List of products and their quantity
     */
    private Map<String, Integer> products;

    /**
     * Constructor
     */
    public Basket() {
        products = new HashMap<String, Integer>();
    }

    /**
     * Set a specific amount of a product in the basket
     * @param product the product we try to add
     * @param amount the amount of product to add
     */
    public void set(String product, Integer amount) {
        Integer exist = products.get(product);
        if (exist == null) {
            products.put(product, 0);
        }
        products.put(product, products.get(product) + amount);
    }

    /**
     * Remote a product from the basket
     * @param product the product we try to add
     */
    public void remove(String product) {
        products.remove(product);
    }

    /**
     * Clear the basket
     */
    public void clear() {
        products.clear();
    }

};