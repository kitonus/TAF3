package com.indivaragroup.event_manager.event_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indivaragroup.event_manager.event_manager.client.TrainingClient;
import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;

@RestController
@RequestMapping("/training_client")
public class TestTrainingClientController {

	@Autowired
	private TrainingClient tc;
	
	@PostMapping("/save_participant")
	public TrainingParticipant saveUsingRemote(@RequestBody TrainingParticipant participant) {
		return tc.save(participant);
	}
	
	@GetMapping("/get_participants/{name}")
	public List<TrainingParticipant> getParticipants(@PathVariable("name") String name){
		return tc.getTrainingParticipants(name);
	}
}
