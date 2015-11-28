package provider.application;


import org.apache.axis2.AxisFault;
import provider.domain.entities.Product;
import provider.api.services.IDomainService;
import provider.domain.services.impl.Service;
import provider.domain.valueobjects.impl.Data;
import provider.exceptions.ProductNotFoundException;
import provider.exceptions.SoldOutException;
import provider.infrastructure.factories.ProviderDataFactory;
import provider.infrastructure.repositories.EventStoreRepository;

import java.util.List;

/**
 * Application
 * public endpoint implementing a given service (Provider)
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class Application implements IDomainService {

    private IDomainService domainService;

    public Application() throws AxisFault {
        this.domainService = new Service();
    }

    @Override
    public List<Product> getListProduct() {
        return this.domainService.getListProduct();
    }

    @Override
    public Product getProduct(String identifier) throws ProductNotFoundException {
        return this.domainService.getProduct(identifier);
    }

    @Override
    public boolean orderProduct(String identifier, int amount) throws ProductNotFoundException, SoldOutException {
        boolean stateOrder = this.domainService.orderProduct(identifier, amount);
        if (!stateOrder) {
            return false;
        }

        /*
        EventStoreRepository providerEventRepository = new EventStoreRepository();
        Data data = new ProviderDataFactory().getData("orderProduct", identifier, amount);
        providerEventRepository.sendEvent(data);
        */

        return stateOrder;
    }
}
