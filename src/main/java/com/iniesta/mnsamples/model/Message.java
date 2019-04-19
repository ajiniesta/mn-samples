package com.iniesta.mnsamples.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {

	private String ts;
	private String topic;
	private String payload;

	@JsonCreator
	public Message(@JsonProperty("ts") String ts, @JsonProperty("topic") String topic,
			@JsonProperty("payload") String payload) {
		this.ts = ts;
		this.topic = topic;
		this.payload = payload;
	}

	public Message() {
	}

	@NotBlank
	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@NotBlank
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "Message {ts=" + ts + ", topic=" + topic + ", payload=" + payload + "}";
	}
	
	
}
