package store.domain.entities;

import java.util.Map;

/**
 * Order
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Order {

    private Map<String, BasketItem> products;

    private double cost;

    public Order(Map<String, BasketItem> products, double cost) {
        this.products = products;
        this.cost = cost;
    }

    public void setProducts(Map<String, BasketItem> products) {
        this.products = products;
    }

    public Map<String, BasketItem> getProducts() {
        return this.products;
    }

    public double getCost() {
        return this.cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
