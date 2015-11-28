package provider.domain.services.impl;

import provider.domain.entities.Product;
import provider.api.services.IDomainService;
import provider.exceptions.ProductNotFoundException;
import provider.exceptions.SoldOutException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service
 * an implementation of a service contract for a provider
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Service implements IDomainService {

    private Map<String, Product> m_refProducts;

    private Map<String, Integer> quantityProducts;

    public Service () {
        m_refProducts = new ConcurrentHashMap<String, Product>();
        m_refProducts.put("Bikini_1", new Product("Bikini_1", "Hot girl....", 100.00, 10));
        m_refProducts.put("Hot_grill_1",new Product("Hot_grill_1", "so hot girl....", 300.00, 10));
        m_refProducts.put("Hot_grill_2",new Product("Hot_grill_2", "so hot girl....", 350.00, 10));
        m_refProducts.put("Hot_grill_3",new Product("Hot_grill_3", "so hot girl....", 400.00, 10));
        m_refProducts.put("Hot_grill_4",new Product("Hot_grill_4", "so hot girl....", 450.00, 10));
        m_refProducts.put("Pamela_1", new Product("Pamela_1", "very hungry grill", 1000.00, 10));

        quantityProducts = new ConcurrentHashMap<String, Integer>();
        quantityProducts.put("Bikini_1", 10);
        quantityProducts.put("Hot_grill_1", 10);
        quantityProducts.put("Hot_grill_2", 10);
        quantityProducts.put("Hot_grill_3", 10);
        quantityProducts.put("Hot_grill_4", 10);
        quantityProducts.put("Pamela_1", 10);
    }

    @Override
    public List<Product> getListProduct() {
        return new ArrayList(m_refProducts.values());
    }

    @Override
    public Product getProduct(String identifier) throws ProductNotFoundException {
        Product product = m_refProducts.get(identifier);
        if (product != null) {
            return product;
        }
        throw new ProductNotFoundException();
    }

    @Override
    public boolean orderProduct(String identifier, int amount) throws ProductNotFoundException, SoldOutException {
        Product product = getProduct(identifier);
        Integer quantity = quantityProducts.get(identifier);

        if (quantity >= amount) {
            quantityProducts.put(identifier, quantity-amount);
            return true;
        }
        throw new SoldOutException();
    }
}
