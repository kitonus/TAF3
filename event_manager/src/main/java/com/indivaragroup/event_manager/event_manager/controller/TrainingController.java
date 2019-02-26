package com.indivaragroup.event_manager.event_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;
import com.indivaragroup.event_manager.event_manager.service.TrainingParticipantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/training")
@Api("Training API")
public class TrainingController {

	@Autowired
	private TrainingParticipantService tpService;
	
	@ApiOperation("Save a paricipant")
	@PostMapping("/participant")
	public TrainingParticipant save(@RequestBody
			TrainingParticipant participant){
		return tpService.save(participant);
	}
	
	@GetMapping("/participant/{name}")
	public List<TrainingParticipant> getTrainingParticipants(
			@PathVariable("name") String name){
		return tpService.findByName(name);
	}
}
