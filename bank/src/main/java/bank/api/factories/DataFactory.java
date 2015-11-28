package bank.api.factories;

import bank.api.valueobjects.IData;

/**
 * DataFactory
 * Interface to define the way we want to persist data
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface DataFactory {

    public IData getData(String eventType, double amount, boolean state, double previousCredit, double newCredit);

}