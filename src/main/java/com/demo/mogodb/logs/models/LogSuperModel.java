package com.demo.mogodb.logs.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "logs")
public class LogSuperModel {

	@Id
	private String id;

	private Integer version;

	private Date timestamp;

	private String message;

}
