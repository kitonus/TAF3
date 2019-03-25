package com.indivaragroup.event_manager.event_manager.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="mst_event")
public class EventEntity implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private UUID eventId;
	
	private String eventName;

	public UUID getEventId() {
		return eventId;
	}

	public void setEventId(UUID eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	
}
