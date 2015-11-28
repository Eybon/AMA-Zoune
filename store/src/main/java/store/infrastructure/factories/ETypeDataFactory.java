package store.infrastructure.factories;

/**
 * ETypeDataFactory
 * Type of action storable
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public enum ETypeDataFactory {

    ADD_TO_CART("AddToCart"),
    REMOVE_FROM_CART("RemoveFromCart"),
    PROCESS_ORDER("ProcessOrder");

    private String qualifier;

    private ETypeDataFactory(String qualifier) {
        this.qualifier = qualifier;
    }

    public String toString() {
        return this.qualifier;
    }

}
