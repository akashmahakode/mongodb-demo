package com.demo.mogodb.logs.service;

import com.demo.mogodb.logs.models.CreateLogRequest;
import com.demo.mogodb.logs.models.Log;
import com.demo.mogodb.logs.models.UpdateLogRequest;
import com.demo.mogodb.logs.repositories.LogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LogService {

    private LogRepository logRepository;

    public LogService(LogRepository repository){
        this.logRepository = repository;
    }

    public List<Log> findAllLogs() {
        return logRepository.findAll();
    }

    public Long countLogs(){
        return logRepository.count();
    }

    public Log createLog(CreateLogRequest createLogRequest) {
        Log log = generateLog(createLogRequest);
        return logRepository.save(log);
    }

    private Log generateLog(CreateLogRequest createLogRequest) {
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setMessage(createLogRequest.getMessage());
        log.setVersion(1);
        log.setTimestamp(new Date());
        return log;
    }

    public Log getLogById(String logId) {
        return logRepository.findById(logId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Log not found by id : "+logId));
    }

    public Log updateLog(String logId, UpdateLogRequest logRequest) {
        Log existingLog = logRepository.findById(logId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Log not found by id : " + logId));
        existingLog.setTimestamp(new Date());
        existingLog.setVersion(existingLog.getVersion()+1);
        existingLog.setMessage(logRequest.getMessage());

        Log updatedLog =  logRepository.save(existingLog);
        log.info("Updated log : {}", updatedLog.getId());

        return updatedLog;

    }
}
