package com.interpark.hermes.webmvctest;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.httpclient.Header;
import com.interpark.hermes.common.httpclient.HttpClient;
import com.interpark.hermes.controller.TestController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(TestController.class)
public class TestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void serviceTest() throws Exception {
        Header header = new Header.Builder().setKey("key").setValue("value").build();

        List<String> list = null;
        for(String s : list) {
            log.info(s);
        }
    }

    @Test
    public void postTest() throws Exception {
        String s  = "{" +
                "\"address\": \"string\"," +
                "\"child\": [" +
                "{" +
                "\"additional\": {" +
                "\"favorite\": \"string\"," +
                "\"hobby\": \"string\"" +
                "}," +
                "\"age\": 0," +
                "\"name\": \"string\"," +
                "\"sex\": \"string\"" +
                "}" +
                "]," +
                "\"daddy\": {" +
                "\"additionalInfo\": {" +
                "\"favorite\": \"string\"," +
                "\"hobby\": \"string\"" +
                "}," +
                "\"age\": 0," +
                "\"name\": \"string\"," +
                "\"sex\": \"string\"" +
                "}," +
                "\"familyName\": \"string\"," +
                "\"mom\": {" +
                "\"additionalInfo\": {" +
                "\"favorite\": \"string\"," +
                "\"hobby\": \"string\"" +
                "}," +
                "\"age\": 0," +
                "\"name\": \"string\"," +
                "\"sex\": \"string\"" +
                "}" +
                "}";

        try {
//            HttpClient httpClient = new HttpClient.Builder("http://localhost/test/family")
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader("accept", "application/xml")
//                    .setCONNECTION_TIME_OUT(3000)
//                    .setREAD_TIME_OUT(3000)
//                    .setMessage(s)
//                    .build();
//
//            log.info(s);
//            log.info(httpClient.post() + "");

        } catch (Exception e) {
            LogUtil u = new LogUtil();
            log.error(u.catchLog(e));
        }
    }

    @Test
    public void getTest() throws Exception {

        try {
//            HttpClient httpClient = new HttpClient.Builder("http://localhost/test/hello")
//                    .setCONNECTION_TIME_OUT(3000)
//                    .setREAD_TIME_OUT(3000)
//                    .build();
//
//            log.info(httpClient.get() + "");

        } catch (Exception e) {
            LogUtil u = new LogUtil();
            log.error(u.catchLog(e));
        }
    }
}
