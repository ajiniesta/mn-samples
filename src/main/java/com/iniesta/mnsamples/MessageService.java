package com.iniesta.mnsamples;

import static com.mongodb.client.model.Filters.eq;

import java.util.List;

import javax.inject.Singleton;
import javax.validation.Valid;

import com.iniesta.mnsamples.controller.MessageConfiguration;
import com.iniesta.mnsamples.model.Message;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class MessageService {

	private MessageConfiguration configuration;
	private MongoClient mongoClient;
	
	public MessageService(MessageConfiguration configuration, MongoClient mongoClient) {
		this.configuration = configuration;
		this.mongoClient = mongoClient;
	}
	
	public Single<List<Message>> list() {
		return Flowable.fromPublisher(
                getCollection()
                    .find()
        ).toList();
	}

	public Single<List<Message>> byTopic(String topic) {
		return Flowable.fromPublisher(
                getCollection()
                    .find(eq("topic", topic))
        ).toList();
	}

    public Maybe<Message> find(String ts) {
        return Flowable.fromPublisher(
                getCollection()
                        .find(eq("ts", ts))
                        .limit(1)
        ).firstElement();
    }
	
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
