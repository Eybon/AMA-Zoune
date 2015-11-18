package provider;

import common.IProduct;


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
	private static List<IProduct> m_refProducts;

	static {
		m_refProducts = Collections.synchronizedList(new ArrayList<IProduct>());		
		m_refProducts.add(new Product("Bikini 1", "Hot girl....", 100.00, 10));
		m_refProducts.add(new Product("Hot grill 1", "so hot girl....", 300.00, 10));
        m_refProducts.add(new Product("Hot grill 2", "so hot girl....", 350.00, 10));
        m_refProducts.add(new Product("Hot grill 3", "so hot girl....", 400.00, 10));
        m_refProducts.add(new Product("Hot grill 4", "so hot girl....", 450.00, 10));
		m_refProducts.add(new Product("Pamela 1", "very hungry grill", 1000.00, 10));
	}

    /**
     * Return the complete list of products
     * @return the complete list of products
     */
	public List<IProduct> getListProduct(){
        return m_refProducts;
	}

    /**
     * Return pieces of information about a given product
     * @param identifier the identifier of a product
     * @return the product corresponding to the identifier
     */
	public IProduct getProduct(String identifier) {
        for (IProduct product : m_refProducts) {
            if (identifier.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

    /**
     * Deduce a produce from the stocks
     * @param identifier the identifier of a product
     * @param amount the amount of product we want to order
     * @return true if the deduction has been made successfuly, false otherwise
     */
	public boolean orderProduct(String identifier, int amount){
        IProduct product = getProduct(identifier);
        if (product.getQuantity() >= amount) {
            ((Product)product).removeQuantity(amount);
            return true;
        }
        return false;
	}


}