package com.indivaragroup.event_manager.event_manager.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import io.kitonus.cache.distributed.CacheManagerConfigAdapter;
import io.kitonus.cache.distributed.CacheNameTree;
import io.kitonus.cache.distributed.TimeToLiveConfig;

@Configuration
@EnableCaching(proxyTargetClass=true)
public class EventManagerCacheConfig extends CacheManagerConfigAdapter{

	@Override
	protected void configureCacheNameTree(CacheNameTree cacheNameTree) {
		cacheNameTree.addName("Events", Arrays.asList("Users"));
	}

	@Override
	protected void applyItemTimeToLiveValues(TimeToLiveConfig ttlConfig) {
		ttlConfig.setDefaultTimeToLive(5, TimeUnit.MINUTES);
	}

}
