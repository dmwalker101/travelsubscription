package demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;

@Entity
public class Subscription {

	@Id
	 
	@Column(unique = true, nullable = false, columnDefinition = "VARCHAR(64)")
	private String subscription_id;

	//@ManyToOne
	//@JoinColumn(name = "events", referencedColumnName = "event_id")
	@OrderColumn(name = "event_sequence")
	@ElementCollection
	private List<String> events;

	public String getSubscription_id() {
		return subscription_id;
	}

	public void setSubscription_id(String subscription_id) {
		this.subscription_id = subscription_id;
	}

	public List<String> getEvents() {
		return events;
	}

	public void setEvents(List<String> events) {
		this.events = events;
	}
}
