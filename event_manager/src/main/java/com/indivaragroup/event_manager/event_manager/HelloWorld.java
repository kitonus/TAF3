package com.indivaragroup.event_manager.event_manager;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
	private final Logger log = LoggerFactory.getLogger(HelloWorld.class); 

	@GetMapping(produces="text/plain")
	public String helloWorld(HttpSession session) {
		log.info("Session ID is: "+session.getId());
		return "Hello World!";
	}
	
	@Secured("ROLE_ADMIN")
    @GetMapping(path="/session/{key}/{value}", produces="text/plain")
    public String saveSessionAttribute(@PathVariable("key") String key, @PathVariable("value") String value,
    		HttpSession session) {
		log.info("Session ID is: "+session.getId());
    	session.setAttribute(key, value);
    	return key+" is set";
    }
    
    @GetMapping(path="/session/{key}", produces="text/plain")
    public String getSessionAttribute(@PathVariable("key") String key,
    		HttpSession session) {
		log.info("Session ID is: "+session.getId());
    	return String.valueOf(session.getAttribute(key));
    }
    
    @GetMapping(path="/whoami")
    public String whoami() {
    	return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
