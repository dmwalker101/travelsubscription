 

# CRUD Subscription  App with Spring Boot


Author Sanat Verma
It features full REST compliance and an embedded H2 database.  mysql has been commented for now



### Requirements

- Maven
- JDK 8

### Running

To build and start the server simply type

```sh
$ mvn spring-boot:run
```

from the root directory.

### Using

You can see what urls are available using curl:

```sh
$ curl localhost:8080
```
You can view existing event objects (including total subscriptions for the event) using a similar request:

```sh
$ curl localhost:8080/events
```


and can create new ones using a POST:

```sh
$ curl -X POST -H "Content-Type:application/json" -d '{ "event_id": "116" ,"event_type": "movieThor1", event_subscriptions" : 0}' localhost:8080/events
```

You can view existing subscription objects using a similar request:

```sh
$ curl localhost:8080/play-subscriptionservice/v1/subscription
```

and can create new ones using a PUT (events are validated with event db):

```sh
$ curl -X PUT -H "Content-Type:application/json" -d '{ "subscription_id" : "DECK345" , "events" : ["116"] }' localhost:8080/play-subscriptionservice/v1/subscription
```

and can update existing subscription (it adds 1 more event to event collection and updates total subs for event) using a POST:

```sh
$ curl -X PUT -H "Content-Type:application/json" -d '{ "subscription_id" : "DECK345" , "events" : ["117"] }' localhost:8080/play-subscriptionservice/v1/subscription/DECK345
```

To import into IDE such as eclipse , import as Maven project from root and run maven clean , then maven install goals to run the Tomcat server or run as springboot app
Chrome Advanced REST client can be used for testing which can be downloaded from Chrome store.

 
### Todo
 - Add unit test instead of current integration test.
 - Add feature to remove specific event from subscription event collection
 - Production switch instead of current in-memory database.
 



	REQUIREMENT
	--------------


Stand-up a small microservice for us using the following spec (Upload on to Github repo to
submit:
 
Microservice spec: 
 
A simple message subscription service that exposes the following functionality: 
   Create a subscription (Would have at least one parameter, which would be a list of message
Types the subscription wants to keep track of) 
   Read the subscription 
   Update the subscription 
   Post a message 
The message would have at least a ‘messageType’ property. 
 
In the response to the &#39;read subscription’ we would like also see how many times a particular
event type has been received by a subscription. There may be more than one subscription
listening for the same event type(s). 
 
Please provide a runnable service written in a Java 8 technology (can be based on spring-boot or
any other of your preference). 
Please implement this as a runnable service written with a Java 8 technology (can be based on
the Spring Framework or any other of your preference). Also provide an explanation as to
instruct how a client would begin using





 
 Provide the source code and instructions for building/running/using the microservice.
