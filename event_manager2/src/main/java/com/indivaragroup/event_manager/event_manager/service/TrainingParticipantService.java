package com.indivaragroup.event_manager.event_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;
import com.indivaragroup.event_manager.event_manager.repository.TrainingParticipantRepository;

@Service
public class TrainingParticipantService {
	
	@Autowired
	private TrainingParticipantRepository tpRepo;

	@Transactional
	public TrainingParticipant save(TrainingParticipant tp) {
		return tpRepo.save(tp);
	}
	
	public List<TrainingParticipant> findByName(String name){
		return tpRepo.findByNameIgnoreCaseLike("%"+name+"%");
	}
	
	public Iterable<TrainingParticipant> findAll(){
		return tpRepo.findAll();
	}
}
