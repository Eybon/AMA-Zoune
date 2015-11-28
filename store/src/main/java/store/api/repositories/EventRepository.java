package store.api.repositories;

import store.api.valueobjects.IData;

/**
 * EventRepository
 * Interface describing how to send a data relative to the domain to persist it
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public interface EventRepository extends Repository {

    void sendEvent(IData event);

}
