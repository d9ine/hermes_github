package com.interpark.hermes.service;

import com.interpark.hermes.common.httpclient.HttpClient;
import com.interpark.hermes.common.httpclient.OkHttpClientBuilder;
import com.interpark.hermes.mapper.TestMapper;
import com.interpark.hermes.models.JPATest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "TestService")
public class TestService implements TestMapper {
    @Autowired
    public TestService(HttpClient httpClient, TestMapper testMapper) {
        this.httpClient = httpClient;
        this.testMapper = testMapper;
    }

    private final TestMapper testMapper;

    private final HttpClient httpClient;

    public Object getEchoFamily(Object obj, Class<?> responseClass) throws Exception {
        return post(obj, responseClass, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML);
    }

    public Object post(Object obj, Class<?> responseClass, MediaType requestMediaType, MediaType responseMediaType) throws Exception {
        OkHttpClientBuilder okHttpClientBuilder = new OkHttpClientBuilder.Builder()
                .setURL("http://localhost/test/echo")
                .addHeader("Content-Type", requestMediaType.toString())
                .addHeader("accept", responseMediaType.toString())
                .setCONNECTION_TIME_OUT(3000)
                .setREAD_TIME_OUT(3000)
                .build();

        httpClient.build(okHttpClientBuilder);

        return httpClient.post(obj, responseClass, requestMediaType, responseMediaType);
    }

    public JPATest selectJPATest(JPATest jpaTest) throws Exception {
        return testMapper.selectJPATest(jpaTest);
    }

    public Integer insertJPATest(JPATest jpaTest) throws Exception {
        return testMapper.insertJPATest(jpaTest);
    }
}
