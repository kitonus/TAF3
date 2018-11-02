package com.indivaragroup.event_manager.event_manager;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping(path="/session/{key}/{value}", produces="text/plain")
    public String saveSessionAttribute(@PathVariable("key") String key, @PathVariable("value") String value,
    	HttpServletRequest request) {
		log.info("Session ID is: "+request.getSession().getId());
    	request.getSession().setAttribute(key, value);
    	return key+" is set";
    }
    
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/testpost/{key}/{value}")
    public String iWannaPost(@PathVariable("key") String key, @PathVariable("value") String value) {
    	return key+", "+value;
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

    @GetMapping(path="/authorities")
    public List<String> authorities() {
    	List<String> authorities = new LinkedList<>();
    	for (GrantedAuthority g : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
    		authorities.add(g.getAuthority());
    	}
    	return authorities;
    }
}
