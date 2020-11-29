package com.demo.mogodb.logs;

import com.demo.mogodb.logs.models.CreateLogRequest;
import com.demo.mogodb.logs.models.Log;
import com.demo.mogodb.logs.models.UpdateLogRequest;
import com.demo.mogodb.logs.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Slf4j
@RestController
@EnableAutoConfiguration
@RequestMapping("/logs")
public class LogController {

	private final LogService logService;

	public LogController(LogService service){
		this.logService = service;
	}

	@GetMapping(value = {"","/"})
	public List<Log> getLogs() {
		return logService.findAllLogs();
	}

	@GetMapping(value = {"/count","/count/"})
	public Long logCount() {
		return logService.countLogs();
	}

	@PostMapping(value = {"","/"})
	public Log saveLog(@RequestBody CreateLogRequest createLogRequest){
		return logService.createLog(createLogRequest);
	}

	@GetMapping(value = {"{id}","/{id}/"})
	public Log getLogById(@PathVariable("id") String logId){
		return logService.getLogById(logId);
	}

	@PatchMapping(value = {"{id}","/{id}/"})
	public Log updateLog(@PathVariable("id") String logId, @RequestBody UpdateLogRequest logRequest){
		return logService.updateLog(logId, logRequest);
	}

	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<Void> handleException(HttpClientErrorException exception){
		log.error("Returning HTTP_STATUS : {}, {}", exception.getStatusCode(), exception.getLocalizedMessage());
		return new ResponseEntity<>(exception.getStatusCode());
	}

}
