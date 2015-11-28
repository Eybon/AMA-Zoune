package store.domain.entities;

import store.generated.provider.ProviderStub;

/**
 * BasketItem
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class BasketItem {

    private ProviderStub.Product product;
    private int quantity;

    public BasketItem() {}

    public BasketItem(ProviderStub.Product product){
        this.product = product;
        this.quantity = 1;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(ProviderStub.Product product) {
        this.product = product;
    }

    public ProviderStub.Product getProduct(){
        return this.product;
    }
}
