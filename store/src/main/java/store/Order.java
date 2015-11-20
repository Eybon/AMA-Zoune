package store;

import java.util.Map;

/**
 * Order
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Order {

    private Map<String, Integer> products;

    private double cost;

    public Order(Map<String, Integer> products, double cost) {
        this.products = products;
        this.cost = cost;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public Map<String, Integer> getProducts() {
        return this.products;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
