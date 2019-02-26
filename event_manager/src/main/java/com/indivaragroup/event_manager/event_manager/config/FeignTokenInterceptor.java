package com.indivaragroup.event_manager.event_manager.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignTokenInterceptor implements RequestInterceptor {
	private final Logger logger = LoggerFactory.getLogger(FeignTokenInterceptor.class);

	@Override
	public void apply(RequestTemplate template) {
		String currentToken = RequestContextHolder.getRequestAttributes().getSessionId();
		if (currentToken != null) {
			logger.debug("==>Current token is: "+currentToken);
			template.header("X-Auth-Token", currentToken);
		}
	}

}
