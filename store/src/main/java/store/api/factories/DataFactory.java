package store.api.factories;

import store.api.valueobjects.IData;

/**
 * DataFactory
 * Interface implementing how the program should build the pieces of data to persist
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface DataFactory {

    public IData getData(String eventType, String reference, int quantity);

}