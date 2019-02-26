package com.indivaragroup.event_manager.event_manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indivaragroup.event_manager.event_manager.dto.TokenDTO;

@RestController
@RequestMapping("/token_info")
public class TokenInfoController {

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.GET })
	public TokenDTO getToken(HttpSession session) {
		TokenDTO dto = new TokenDTO();
		dto.setToken(session.getId());
		dto.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		dto.setAuthorities(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		return dto;
	}
}
