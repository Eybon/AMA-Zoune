package store.domain.entities;

import store.generated.provider.ProviderStub;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Basket
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
@XmlRootElement
public  class Basket {

    /**
     * List of products and their quantity
     */
    private Map<String, BasketItem> products;

    /**
     * Constructor
     */
    public Basket() {
        products = new HashMap<String, BasketItem>();
    }

    /**
     * Set a specific amount of a product in the basket
     * @param product the product we try to add
     * @param amount the amount of product to add
     */
    public void set(ProviderStub.Product product, Integer amount) {
        BasketItem exist = products.get(product.getName());
        if (exist == null) {
            products.put(product.getName(), new BasketItem(product));
            exist = products.get(product.getName());
        }
        if (amount <= 0) {
            products.remove(product.getName());
            return;
        }
        exist.setQuantity(amount);
        products.put(product.getName(), exist);
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

    /**
     * Returns the products in the current basket
     * @return the products in the current basket
     */
    public Map<String, BasketItem> getProducts() {
        return this.products;
    }

    public BasketItem[] getItems() {
        BasketItem[] items = new BasketItem[products.size()];
        Set<String> refs = products.keySet();
        int i = 0;
        for (String ref : refs) {
            items[i++] = products.get(ref);
        }

        return items;
    }

    public void setItems(BasketItem[] items) {
        products.clear();

        for (BasketItem item : items) {
            products.put(item.getProduct().getName(), item);
        }
    }
};