package com.interpark.hermes.controller;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.Family;
import com.interpark.hermes.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/test")
@Api(tags = {"TestController"})
public class TestController extends LogUtil {
    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @ApiOperation(value = "get 예제")
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "post, 연동, aop 예제")
    @PostMapping(value = "/family",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Family family(@RequestBody Family family) throws Exception {
        startJob();

        try {
            Family fa = (Family) testService.getEchoFamily(family, Family.class);
            log.info(Marshaller.marshall(fa, MediaType.APPLICATION_XML));

        } catch (Exception e) {
            log.error(catchLog(e));
            throw e;
        }

        finishJob();
        log.info(executeTimeLog());

        return family;
    }

    @ApiOperation(value = "에코서비스")
    @PostMapping(value = "/echo",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Family echo(@RequestBody Family family) {
        return family;
    }
}


