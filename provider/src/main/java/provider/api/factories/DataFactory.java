package provider.api.factories;


import provider.api.valueobjects.IData;

/**
 * DataFactory
 * Interface that should be implemented by every factory belonging to the current domain
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface DataFactory {

    public IData getData(String eventType, String reference, int quantity);

}