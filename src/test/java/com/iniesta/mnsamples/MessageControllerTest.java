package com.iniesta.mnsamples;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runners.MethodSorters;

import com.iniesta.mnsamples.controller.MessageConfiguration;
import com.iniesta.mnsamples.controller.MessageOperations;
import com.iniesta.mnsamples.model.Message;
import com.mongodb.reactivestreams.client.MongoClient;

import io.micronaut.configuration.mongo.reactive.MongoSettings;
import io.micronaut.context.ApplicationContext;
import io.micronaut.core.io.socket.SocketUtils;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.runtime.server.EmbeddedServer;
import io.reactivex.Flowable;

import org.junit.FixMethodOrder;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MessageControllerTest {

	static EmbeddedServer embeddedServer;
	
	@BeforeClass
    public static void setup() {
        embeddedServer = ApplicationContext.run(
                EmbeddedServer.class,
                CollectionUtils.mapOf(
                        MongoSettings.MONGODB_URI, "mongodb://localhost:" + SocketUtils.findAvailableTcpPort(),
                        "consul.client.registration.enabled",false
                )
        );
    }

    @AfterClass
    public static void cleanup() {
        if (embeddedServer != null) {
            embeddedServer.stop();
        }
    }

    @After
    public void cleanupData() {
        ApplicationContext applicationContext = embeddedServer.getApplicationContext();
        MongoClient mongoClient = applicationContext.getBean(MongoClient.class);
        MessageConfiguration config = applicationContext.getBean(MessageConfiguration.class);
        // drop the data
        Flowable.fromPublisher(mongoClient.getDatabase(config.getDatabaseName())
                    .drop()).blockingFirst();
    }

    @Test
    public void testListMessages() {
    	MessageOperations client = embeddedServer.getApplicationContext().getBean(MessageOperations.class);
    	
    	List<Message> initialMsgs = client.list().blockingGet();
    	assertEquals(0, initialMsgs.size());
    }
}
