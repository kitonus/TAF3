package com.indivaragroup.event_manager.event_manager.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indivaragroup.event_manager.event_manager.entity.EventEntity;
import com.indivaragroup.event_manager.event_manager.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService service;
	
	@PostMapping()
	public EventEntity save(@RequestBody EventEntity event) {
		service.saveEvent(event);
		return service.getEvent(event.getEventId());
	}
	
	
	@GetMapping
	public List<EventEntity> all(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public EventEntity findOne(@PathVariable("id") UUID id) {
		return service.getEvent(id);
	}
	
}
