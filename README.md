# AMA-Zoune

AMA-Zoune (or Ask Me Anything Zoune) is a university project which aims to use SOAP services through Java development.

AMA-Zoune is also a huge joke.

# Required :

* Maven (used for dependencies)
* ~~An EventStore server (see https://geteventstore.com/downloads/)~~
* ~~Akka library installed (see http://akka.io/downloads/)~~
* An Application Server (WSo2 for example)

# How to build

* for client :
> mvn clean install

* for store, provider and bank modules (in their package) :
> mvn package

# How to run :

Here is a way to do so with WSo2 server :

* Retrieve every .jar file produced previously (store.jar, provider.jar, bank.jar in their respective target/ folder)
* Upload them as "Jar Service" on your server
* When you're asked about the classes you want to expose, check either bank.application.Application, either store.application.Application, either provider.provider.Application depending on the uploaded component
* When you're asked about the methods to expose, check everything then validate
* When you're over with the .jar files, now upload your .war file (in client/target/ folder) into "Web Applications"
* Once every uploaded service is up on on, just go to the client url (something along the lines of http://localhost:SERVER_PORT/Client/index.jsp)

# Warning :

* Remember do edit URL in the store so that you can fetch your own implementation bank and provider services (it actually fetches http://localhost/...)
* If you want to do Cross-Platform requests (from the client to the store), be sure to activate this setting on your application server.

# Issues :

This is a one-week-rush-work so there must be some issues while browsing the client interface or while consuming services. I'm not sure to have the time for this project anymore so feel free to submit pull requests :)

# Authors :

* Maxime BERGEON (Cr3aHal0)
* Antoine FORGEROU (Eybon)