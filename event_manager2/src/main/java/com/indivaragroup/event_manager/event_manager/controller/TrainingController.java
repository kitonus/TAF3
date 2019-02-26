package com.indivaragroup.event_manager.event_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;
import com.indivaragroup.event_manager.event_manager.exception.DataNotFoundException;
import com.indivaragroup.event_manager.event_manager.service.TrainingParticipantService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/training2")
@Api("Training API")
public class TrainingController {

	@Autowired
	private TrainingParticipantService tpService;
	
	
	@Secured("ROLE_ADMIN")
	@ApiOperation("Save a paricipant")
	@PostMapping("/participant")
	public TrainingParticipant save(@RequestBody
			TrainingParticipant participant){
		return tpService.save(participant);
	}
	
	@GetMapping("/participant/{name}")
	public List<TrainingParticipant> getTrainingParticipants(
			@PathVariable("name") String name){
		List<TrainingParticipant> l = tpService.findByName(name);
		System.out.println("Get training participant "+name);
		if (l.isEmpty()) {
			throw new DataNotFoundException("Training participant not found"); 
		}
		return l;
	}
	
	@RequestMapping(value="/participant/all", method= {RequestMethod.GET, RequestMethod.POST})
	public Iterable<TrainingParticipant> getAll(){
		throw new UnsupportedOperationException("This is unsuppored!");
		//return tpService.findAll();
	}
}
