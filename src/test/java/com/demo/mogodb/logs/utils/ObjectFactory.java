package com.demo.mogodb.logs.utils;

import com.demo.mogodb.logs.models.Log;

import java.util.Date;
import java.util.UUID;

public class ObjectFactory {

    public static Log createLog(){
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setMessage("Hello");
        log.setVersion(1);
        log.setTimestamp(new Date());

        return log;
    }
}
