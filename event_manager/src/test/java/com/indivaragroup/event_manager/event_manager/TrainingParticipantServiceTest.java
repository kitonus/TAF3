package com.indivaragroup.event_manager.event_manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.indivaragroup.event_manager.event_manager.entity.TrainingParticipant;
import com.indivaragroup.event_manager.event_manager.service.TrainingParticipantService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainingParticipantServiceTest {

	@Autowired
	TrainingParticipantService tpService;
	
	@Test
	public void test() {
		List<TrainingParticipant> findByName = tpService.findByName("t");
		assertNotNull(findByName);
		assertTrue(findByName.size() > 0);
	}
}
