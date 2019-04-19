package com.iniesta.mnsamples.controller;

import java.util.List;

import javax.validation.Valid;

import com.iniesta.mnsamples.MessageService;
import com.iniesta.mnsamples.model.Message;

import io.micronaut.configuration.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Controller("/${samples.api.version}/msgs")
@Validated
public class MessageController implements MessageOperations{

	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@Override
    @HystrixCommand
	public Single<List<Message>> list() {
		return messageService.list();
	}

	@Override
	public Single<List<Message>> byTopic(String topic) {
		return messageService.byTopic(topic);
	}

	@Override
    public Maybe<Message> find(String ts) {
        return messageService.find(ts);
    }
	
	@Override
	public Single<Message> save(@Valid Message message) {
		return messageService.save(message);
	}
	
}
