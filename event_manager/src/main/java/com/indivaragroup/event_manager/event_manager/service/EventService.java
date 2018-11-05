package com.indivaragroup.event_manager.event_manager.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indivaragroup.event_manager.event_manager.entity.EventEntity;
import com.indivaragroup.event_manager.event_manager.repository.EventRepostory;

@Service
public class EventService {

	@Autowired
	private EventRepostory repo;
	
	@Transactional
	public void saveEvent(EventEntity event) {
		repo.save(event);
	}
	
	public EventEntity getEvent(UUID eventId) {
		Optional<EventEntity> result = repo.findById(eventId);
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	public List<EventEntity> findAll(){
		return repo.getAll();
	}
}
