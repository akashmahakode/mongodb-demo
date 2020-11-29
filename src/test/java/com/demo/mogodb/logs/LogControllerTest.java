package com.demo.mogodb.logs;

import com.demo.mogodb.logs.utils.ObjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.mogodb.logs.models.Log;
import com.demo.mogodb.logs.repositories.LogRepository;
import com.demo.mogodb.logs.service.LogService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LogController.class)
public class LogControllerTest {

    private static final String URL_GET_LOG = "/logs/{id}";
    private static final String URL_GET_ALL_LOGS = "/logs";
    private static final String URL_CREATE_LOG = "/logs/";
    private static final String URL_COUNT_LOGS = "/logs/count";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LogService logService;

    @MockBean
    private LogRepository logRepository;

    /**
     * Test case to test Endpoint to retrieve log by identifier i.e GET /logs/{id}
     * @throws Exception
     */
    @Test
    public void testGetLogById() throws Exception{
        Log log = ObjectFactory.createLog();
        when(logService.getLogById(anyString())).thenReturn(log);
        String response = mvc.perform(get(URL_GET_LOG, log.getId()))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();

        assertThat(response).contains(log.getId());
    }

    /**
     *  Test case to check EP to count the number of logs i.e. GET /logs/count
     * @throws Exception
     */
    @Test
    public void testGetLogsCount() throws Exception{
        when(logService.countLogs()).thenReturn(2l);
        String logCountString = mvc.perform(get(URL_COUNT_LOGS))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse().getContentAsString();
        Integer logCount = Integer.parseInt(logCountString);
        Assertions.assertThat(logCount).isEqualTo(2);
    }

    /**
     * Test case to retrieve all logs. I.e. testing GET /logs endpoint
     * @throws Exception
     */
    @Test
    public void testGetAllLogs() throws Exception{
        Log log1 = ObjectFactory.createLog();
        Log log2 = ObjectFactory.createLog();
        Log log3 = ObjectFactory.createLog();

        when(logService.findAllLogs()).thenReturn(Arrays.asList(log1, log2, log3));

        String response = mvc.perform(get(URL_GET_ALL_LOGS)).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        Assertions.assertThat(response).contains(log1.getId(), log2.getId());

    }

    /**
     * Test case to check if GET /logs/{id} is called for non-existing log
     * @throws Exception
     */
    @Test
    public void testLogIdNotFound() throws Exception{
        when(logService.getLogById(anyString())).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));
        mvc.perform(get(URL_GET_LOG, "1234")).andExpect(status().isNotFound());
    }
}