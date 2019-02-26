package com.indivaragroup.event_manager.event_manager.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;

@FeignClient("event-manager2")
@RequestMapping("/training2")
public interface TrainingClient {

	@PostMapping("/participant")
	public TrainingParticipant save(@RequestBody
			TrainingParticipant participant);

	@GetMapping("/participant/{name}")
	public List<TrainingParticipant> getTrainingParticipants(
			@PathVariable("name") String name);
}
