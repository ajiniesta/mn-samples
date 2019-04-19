package com.iniesta.mnsamples.job;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.micronaut.scheduling.annotation.Scheduled;

@Singleton
public class InsertJob {
	private static final Logger LOG = LoggerFactory.getLogger(InsertJob.class);
	
	private DataGenerator dataGenerator;
	
	public InsertJob(DataGenerator dataGenerator) {
		this.dataGenerator = dataGenerator;
	}
	
	@Scheduled(fixedDelay = "10s")
	void executeEveryTen() {
		LOG.info("Inserting new message in the app");
		dataGenerator.saveRandomMsg();
	}
}
