package com.indivaragroup.event_manager.event_manager.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.indivaragroup.event_manager.event_manager.entity.EventEntity;

public interface EventRepostory extends PagingAndSortingRepository<EventEntity, UUID> {

	@Query("from EventEntity order by eventName")
	public List<EventEntity> getAll();
}
