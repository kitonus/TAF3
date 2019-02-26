package com.indivaragroup.event_manager.event_manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.hazelcast.HazelcastSessionRepository;
import org.springframework.session.hazelcast.PrincipalNameExtractor;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapAttributeConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapIndexConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@EnableHazelcastHttpSession
@Configuration
public class HazelcastConfig extends AbstractHttpSessionApplicationInitializer {

	@Bean
	public HazelcastInstance hazelcastInstance() {
		MapAttributeConfig attributeConfig = new MapAttributeConfig()
				.setName(HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE)
				.setExtractor(PrincipalNameExtractor.class.getName());

		Config config = new Config();

		config.getMapConfig(HazelcastSessionRepository.DEFAULT_SESSION_MAP_NAME) 
				.addMapAttributeConfig(attributeConfig)
				.addMapIndexConfig(new MapIndexConfig(
						HazelcastSessionRepository.PRINCIPAL_NAME_ATTRIBUTE, false));
		
		//add config for cache
        MapConfig trainingParticipantCache = new MapConfig();
        trainingParticipantCache.setTimeToLiveSeconds(20);
        trainingParticipantCache.setEvictionPolicy(EvictionPolicy.LFU);

        config.getMapConfigs().put("trainingParticipantCache",trainingParticipantCache);

 		

		return Hazelcast.newHazelcastInstance(config); 
	}
	
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken(); 
	}
	
}
