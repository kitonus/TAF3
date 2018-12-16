package com.indivaragroup.test.consul.test_consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/hello")
public class TestConsulApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestConsulApplication.class, args);
	}


	@GetMapping("/world")
	public String helloWorld() {
		return "Hello World!";
	}
}

