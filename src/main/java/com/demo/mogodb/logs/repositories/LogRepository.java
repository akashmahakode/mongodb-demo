package com.demo.mogodb.logs.repositories;

import com.demo.mogodb.logs.models.Log;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LogRepository extends MongoRepository<Log, String> {

}
