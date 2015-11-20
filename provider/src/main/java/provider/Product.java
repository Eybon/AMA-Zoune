package provider;

/**
 * Product class
 *
 * How is representing a product from the provided perspective
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Product
{

    /**
     * The name of the product
     */
	String m_name;

    /**
     * the description of the product
     */
	String m_description;

    /**
     * the price of the product
     */
	double m_price;

    /**
     * the quantity available
     */
    int m_quantity;

    /**
     * Constructor
     * @param name the name of the product
     * @param desc the description of the product
     * @param price the price of the product
     * @param quantity the quantity available
     */
	public Product(String name,String desc,double price, int quantity){
		m_name = name;
		m_description = desc;
		m_price = price;
        m_quantity = quantity;
	}


    public String getName() {
        return m_name;
    }

    public double getPrice() {
        return m_price;
    }

    public String getDescription() {
        return m_description;
    }

    public int getQuantity() {
        return m_quantity;
    }

    /**
     * Reduce a product quantity
     * @param quantity the quantity to deduce
     */
    public void removeQuantity(int quantity) {
        m_quantity -= quantity;
    }
}