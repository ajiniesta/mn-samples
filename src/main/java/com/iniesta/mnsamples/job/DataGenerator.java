package com.iniesta.mnsamples.job;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iniesta.mnsamples.MessageService;
import com.iniesta.mnsamples.model.Message;

@Singleton
public class DataGenerator {
	private static final Logger LOG = LoggerFactory.getLogger(InsertJob.class);
	
	private MessageService messageService;
	private transient int payload;
	
	public DataGenerator(MessageService messageService) {
		this.messageService = messageService;
	}
	
	public void saveRandomMsg() {
		Message m = new Message(new Long(System.currentTimeMillis()).toString(), "scheduled", (++payload)+"");
		Message saved = messageService.save(m).blockingGet();
		LOG.info(String.format("Saving new message %s", saved.toString()));
	}
}
