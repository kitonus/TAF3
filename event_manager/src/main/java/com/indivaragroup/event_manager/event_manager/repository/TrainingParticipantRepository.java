package com.indivaragroup.event_manager.event_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;

public interface TrainingParticipantRepository extends PagingAndSortingRepository<TrainingParticipant, UUID> {

	public List<TrainingParticipant> findByNameIgnoreCaseLike(String name);
}
