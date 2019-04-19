package com.iniesta.mnsamples.controller;

import java.util.List;

import javax.validation.Valid;

import com.iniesta.mnsamples.model.Message;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Validated
public interface MessageOperations {

	@Get("/")
	Single<List<Message>> list();
	
	@Get("/topic/{topic}")
    Single<List<Message>> byTopic(String topic);

    @Get("/{ts}")
    Maybe<Message> find(String ts);

    @Post("/")
    Single<Message> save(@Valid @Body Message message);

}
