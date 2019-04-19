package com.iniesta.mnsamples.controller;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import javax.validation.Valid;

import com.iniesta.mnsamples.model.Message;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.micronaut.configuration.hystrix.annotation.HystrixCommand;
import io.micronaut.http.annotation.Controller;
import io.micronaut.validation.Validated;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Controller("/${samples.api.version}/msgs")
@Validated
public class MessageController implements MessageOperations{

	private MessageConfiguration configuration;
	private MongoClient mongoClient;
	
	public MessageController(MessageConfiguration configuration, MongoClient mongoClient) {
		this.configuration = configuration;
		this.mongoClient = mongoClient;
	}

	@Override
    @HystrixCommand
	public Single<List<Message>> list() {
		return Flowable.fromPublisher(
                getCollection()
                    .find()
        ).toList();
	}

	@Override
	public Single<List<Message>> byTopic(String topic) {
		return Flowable.fromPublisher(
                getCollection()
                    .find(eq("topic", topic))
        ).toList();
	}

	@Override
    public Maybe<Message> find(String ts) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(eq("ts", ts))
                        .limit(1)
        ).firstElement();
    }
	
	@Override
	public Single<Message> save(@Valid Message message) {
		return find(message.getTs())
                .switchIfEmpty(
                        Single.fromPublisher(getCollection().insertOne(message))
                               .map(success -> message)
                );
	}

    private MongoCollection<Message> getCollection() {
        return mongoClient
                .getDatabase(configuration.getDatabaseName())
                .getCollection(configuration.getCollectionName(), Message.class);
    }
	
}
