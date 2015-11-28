package bank.infrastructure.repositories;

import akka.actor.Status;
import akka.actor.UntypedActor;
import eventstore.EsException;
import eventstore.WriteEventsCompleted;

/**
 * WriteResult
 * Write operator : https://www.geteventstore.com/blog/20140904/event-store-3.0.0-client-api-for-jvm-clients/index.html
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class WriteResult extends UntypedActor {

    public void onReceive(Object message) throws Exception {
        if (message instanceof WriteEventsCompleted) {
            final WriteEventsCompleted completed = (WriteEventsCompleted) message;
            //log.info("range: {}, position: {}", completed.numbersRange(), completed.position());
        }
        else if (message instanceof Status.Failure) {
            final Status.Failure failure = ((Status.Failure) message);
            final EsException exception = (EsException) failure.cause();
            //log.error(exception, exception.toString());
        }
        else
        {
            unhandled(message);
        }
        context().system().shutdown();
    }

}