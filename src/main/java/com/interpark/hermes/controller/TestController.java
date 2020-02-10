package com.interpark.hermes.controller;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.common.httpclient.HttpClient;
import com.interpark.hermes.common.httpclient.OkHttpClientBuilder;
import com.interpark.hermes.models.Family;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping(value="/test")
@Api(tags = {"TestController"})
public class TestController extends LogUtil {
    @GetMapping(value = "/hello")
    public String hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        startJob();
        finishJob();
        log.info(executeTimeLog());

        return "hello";
    }

    @PostMapping(value = "/postTest",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Family postTest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                        @RequestBody Family family) throws Exception {

        return family;
    }

    @Autowired
    HttpClient httpClient;

    @PostMapping(value = "/family",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Family hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                        @RequestBody Family family) throws Exception {
        startJob();

        try {
            Marshaller m = new Marshaller();
            String message = m.marshall(family, Marshaller.MEDIA_TYPE.JSON);

            OkHttpClientBuilder okHttpClientBuilder = new OkHttpClientBuilder.Builder()
                    .setURL("http://localhost/test/postTest")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("accept", "application/json")
                    .setCONNECTION_TIME_OUT(3000)
                    .setREAD_TIME_OUT(3000)
                    .build();

            httpClient.build(okHttpClientBuilder);

            Family fa = (Family) httpClient.post(family, Family.class);
            log.info(fa.getName());

        } catch (Exception e) {
            log.error(catchLog(e));
            throw e;
        }

        finishJob();
        log.info(executeTimeLog());

        return family;
    }
}


