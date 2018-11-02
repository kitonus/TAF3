package com.indivaragroup.event_manager.manager_admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class ManagerAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagerAdminApplication.class, args);
	}
	
}
