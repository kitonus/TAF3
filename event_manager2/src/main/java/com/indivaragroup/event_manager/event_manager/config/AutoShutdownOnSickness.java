package com.indivaragroup.event_manager.event_manager.config;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.jdbc.DataSourceHealthIndicator;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AutoShutdownOnSickness implements InitializingBean, DisposableBean{
	private final Logger log = LoggerFactory.getLogger(AutoShutdownOnSickness.class);

	@Autowired
	private DataSourceHealthIndicator dsHealth;
	
	@Autowired
	private ConfigurableApplicationContext context;
	
	@Value("${app.initial_auto_shutdown_check_seconds:60}")
	private int initialAutoShutdownCheckSeconds;

	@Value("${app.auto_shutdown_check_interval_seconds:10}")
	private int autoShutdownCheckIntervalSeconds;

	@Override
	public void destroy() throws Exception {
		try {
			exec.shutdownNow();
		} catch(Throwable t) {}
	}
	
	private final ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);

	@Override
	public void afterPropertiesSet() throws Exception {
		
		exec.scheduleWithFixedDelay(()->{
			if (log.isDebugEnabled()) {
				log.debug(">>>Checking DataSource health");
			}
			Status stat = dsHealth.health().getStatus();
			if (Status.OUT_OF_SERVICE.equals(stat) || Status.DOWN.equals(stat)) {
				log.info("Datasource is sick!!! Shutting down");
				context.close();
				System.exit(1);
			} else {
				log.debug("GOOD! >>>DataSource is HEALTHY!");
			}
		}, this.initialAutoShutdownCheckSeconds, this.autoShutdownCheckIntervalSeconds, TimeUnit.SECONDS);
	}
	
	
}
