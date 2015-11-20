package provider;

import exceptions.provider.ProductNotFoundException;
import exceptions.provider.SoldOutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Provider
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Provider
{

    /**
     * List of products
     */
	private List<Product> m_refProducts;

    public static void main(String[] args) {}

    public Provider () {
        m_refProducts = Collections.synchronizedList(new ArrayList<Product>());
        m_refProducts.add(new Product("Bikini_1", "Hot girl....", 100.00, 10));
        m_refProducts.add(new Product("Hot_grill_1", "so hot girl....", 300.00, 10));
        m_refProducts.add(new Product("Hot_grill_2", "so hot girl....", 350.00, 10));
        m_refProducts.add(new Product("Hot_grill_3", "so hot girl....", 400.00, 10));
        m_refProducts.add(new Product("Hot_grill_4", "so hot girl....", 450.00, 10));
        m_refProducts.add(new Product("Pamela_1", "very hungry grill", 1000.00, 10));
    }

    /**
     * Return the complete list of products
     * @return the complete list of products
     */
	public List<Product> getListProduct(){
        return m_refProducts;
	}

    /**
     * Return pieces of information about a given product
     * @param identifier the identifier of a product
     * @return the product corresponding to the identifier
     * @throws ProductNotFoundException if the product does not exist
     */
	public Product getProduct(String identifier) throws ProductNotFoundException {
        for (Product product : m_refProducts) {
            if (identifier.equals(product.getName())) {
                return product;
            }
        }
        throw new ProductNotFoundException();
    }

    /**
     * Deduce a produce from the stocks
     * @param identifier the identifier of a product
     * @param amount the amount of product we want to order
     * @return true if the deduction has been made successfuly, false otherwise
     */
	public boolean orderProduct(String identifier, int amount) throws ProductNotFoundException, SoldOutException {
        Product product = getProduct(identifier);
        if (product.getQuantity() >= amount) {
            product.removeQuantity(amount);
            return true;
        }
        throw new SoldOutException();
	}


}