package com.demo.mogodb.logs.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "logs")
public class Log extends LogSuperModel {

	public Log() {
	}

	@Override
	public String toString() {
		return String.format("Log[id=%s, message='%s']", getId(), getMessage());
	}

}
