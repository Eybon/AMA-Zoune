package provider;

import common.IProduct;

/**
 * Product class
 *
 * How is representing a product from the provided perspective
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Product implements IProduct
{

	String m_name;
	String m_description;
	double m_price;
    int m_quantity;

	public Product(String name,String desc,double price, int quantity){
		m_name = name;
		m_description = desc;
		m_price = price;
        m_quantity = quantity;
	}


    @Override
    public String getName() {
        return null;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public int getQuantity() {
        return m_quantity;
    }

    public void removeQuantity(int quantity) {
        m_quantity -= quantity;
    }
}