package demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OrderColumn;

@Entity
public class Event {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(unique = true, nullable = false, columnDefinition = "VARCHAR(32)")
	private String event_id;

	 
	private String event_type;
	
	private Long event_subscriptions;


	public String getEvent_id() {
		return event_id;
	}


	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}


	public String getEvent_type() {
		return event_type;
	}


	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	
	public Long getEvent_subscriptions() {
		return event_subscriptions;
	}


	public void setEvent_type(Long event_subscriptions) {
		this.event_subscriptions = event_subscriptions;
	} 

 
}
