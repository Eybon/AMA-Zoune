package provider.api.services;

import provider.domain.entities.Product;
import provider.exceptions.ProductNotFoundException;
import provider.exceptions.SoldOutException;

import java.util.List;

/**
 * IDomainService
 * Methods that should be provided by any domain of the current service
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface IDomainService {

    /**
     * Returns the list of products registered by the provider
     * @return the list of products registered by the provider
     */
    List<Product> getListProduct();

    /**
     * Returns the products corresponding to the given identifier
     * @param identifier the name of the product
     * @return the corresponding product if it exists
     * @throws ProductNotFoundException incase the product isn't known by the provider
     */
    Product getProduct(String identifier) throws ProductNotFoundException;

    /**
     * Try to order a product and deduce it from the stock
     * @param identifier name of a product
     * @param amount amount of product to deduce
     * @return true if the deduction has been made successfully
     * @throws ProductNotFoundException incase the product isn't known by the provider
     * @throws SoldOutException incase the product is out of stock
     */
    boolean orderProduct(String identifier, int amount) throws ProductNotFoundException, SoldOutException;

}