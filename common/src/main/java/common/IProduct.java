package common;

/**
 * Product from store view
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface IProduct
{

	public String getName();
	public double getPrice();
	public String getDescription();
    public int getQuantity();

}