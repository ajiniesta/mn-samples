package com.iniesta.mnsamples.controller;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("messages")
public class MessageConfiguration {

	private String databaseName = "messagestore";
	private String collectionName = "messages";

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getCollectionName() {
		return collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

}
