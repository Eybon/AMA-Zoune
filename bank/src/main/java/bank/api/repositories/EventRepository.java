package bank.api.repositories;

import bank.api.valueobjects.IData;

/**
 * EventRepository
 * The way we persist data
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface EventRepository extends Repository {

    void sendEvent(IData event);

}
