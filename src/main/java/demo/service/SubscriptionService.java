package demo.service;

import demo.repository.EventRepository;
import demo.repository.SubscriptionRepository;
import demo.exception.HTTP404Exception;
import demo.model.Event;
import demo.model.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private EventRepository eventRepository;

	public SubscriptionService() {
	}

	public Subscription createSubscription(Subscription subscription) {

		/*   VERIFY ALL EVENTS ARE VALID */

		for (String eventReceived : subscription.getEvents()) {
			Event eventVerified = eventRepository.findOne(eventReceived.trim());

			if (null == eventVerified)
				throw new HTTP404Exception(eventReceived + " event not valid for create subscription");
			else

			{
				eventVerified.setEvent_type(eventVerified.getEvent_subscriptions() + 1);
				eventRepository.save(eventVerified);
			}
		}

		return subscriptionRepository.save(subscription);

	}

	public Subscription getSubscription(String subscription_id) {
		return subscriptionRepository.findOne(subscription_id);
	}

	public void updateSubscription(Subscription subscription) {

		/* Verify event exist. All or nothing rule for collection of event sent */
		for (String eventReceived : subscription.getEvents()) {
			Event eventVerified = eventRepository.findOne(eventReceived.trim());

			if (null == eventVerified)
				throw new HTTP404Exception(eventReceived + " event not valid for updating subscription ");
			else {
				eventVerified.setEvent_type(eventVerified.getEvent_subscriptions() + 1);
				eventRepository.save(eventVerified);
			}
		}

		List<String> events = new ArrayList<String>();

		/* Retrieve from db */
		Subscription subRetrieved = this.getSubscription(subscription.getSubscription_id());

		/* Append to collection */
		for (String eventRetrieved : subRetrieved.getEvents())
			events.add(eventRetrieved);

		/* Append to collection the received subscription */
		for (String eventReceieved : subscription.getEvents())
			// TO DO verify event exist
			events.add(eventReceieved);

		subscription.setEvents(events);

		subscriptionRepository.save(subscription);
	}

	public void deleteSubscription(String subscription_id) {
		subscriptionRepository.delete(subscription_id);
	}

	public Iterable<Subscription> getAllSubscription() {
		return subscriptionRepository.findAll();

	}
}
