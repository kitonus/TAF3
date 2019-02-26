package com.indivaragroup.event_manager.event_manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Configuration
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer() {
		super(SecurityConfig.class, HazelcastConfig.class);
	}
}
