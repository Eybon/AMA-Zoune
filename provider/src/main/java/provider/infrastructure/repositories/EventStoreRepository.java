package provider.infrastructure.repositories;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import eventstore.EventData;
import eventstore.Settings;
import eventstore.WriteEvents;
import eventstore.j.EventDataBuilder;
import eventstore.j.SettingsBuilder;
import eventstore.j.WriteEventsBuilder;
import eventstore.tcp.ConnectionActor;
import provider.api.repositories.EventRepository;
import provider.api.valueobjects.IData;

import java.net.InetSocketAddress;
import java.util.UUID;

/**
 * EventStoreRepository
 * Implementation of EventRepository
 *
 * @version 0.1
 * @author Antoine Forgerou
 * @author Maxime BERGEON
 */
public class EventStoreRepository implements EventRepository {

    @Override
    public void sendEvent(IData data) {
        ActorSystem system = ActorSystem.create();

        Settings settings = new SettingsBuilder()
                .address(new InetSocketAddress("localhost", 2113))
                .defaultCredentials("admin", "changeit")
                .build();

        ActorRef connection = system.actorOf(ConnectionActor.getProps(settings));
        ActorRef writeResult = system.actorOf(Props.create(WriteResult.class));

        EventData event = new EventDataBuilder(data.getValue())
                .eventId(UUID.randomUUID())
                .data(data.getValue())
                .metadata(data.getMetadata())
                .build();

        WriteEvents writeEvents = new WriteEventsBuilder("Provider")
                .addEvent(event)
                .expectAnyVersion()
                .build();

        connection.tell(writeEvents, writeResult);
    }

}
