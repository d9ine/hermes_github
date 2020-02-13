package com.interpark.hermes;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.common.httpclient.HttpClient;
import com.interpark.hermes.common.httpclient.OkHttpClientBuilder;
import com.interpark.hermes.models.Family;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

/** 통합테스트 시작시 모든 리소스(db커넥션등) 로드됨 **/

@Slf4j
@SpringBootTest
class HermesApplicationTests {
    @Test
    void marshallSample() throws Exception {
        String s  = "{\n" +
                "  \"address\": \"string\",\n" +
                "  \"child\": [\n" +
                "    {\n" +
                "      \"additionalInfo\": {\n" +
                "        \"favorite\": \"string\",\n" +
                "        \"hobby\": \"string\"\n" +
                "      },\n" +
                "      \"age\": \"string\",\n" +
                "      \"name\": \"string\",\n" +
                "      \"sex\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"daddy\": {\n" +
                "    \"additionalInfo\": {\n" +
                "      \"favorite\": \"string\",\n" +
                "      \"hobby\": \"string\"\n" +
                "    },\n" +
                "    \"age\": 0,\n" +
                "    \"name\": \"string\",\n" +
                "    \"sex\": \"string\"\n" +
                "  },\n" +
                "  \"familyName\": \"string\",\n" +
                "  \"mom\": {\n" +
                "    \"additionalInfo\": {\n" +
                "      \"favorite\": \"string\",\n" +
                "      \"hobby\": \"string\"\n" +
                "    },\n" +
                "    \"age\": 0,\n" +
                "    \"name\": \"string\",\n" +
                "    \"sex\": \"string\"\n" +
                "  }\n" +
                "}";

        Family family = (Family) Marshaller.unMarshall(s, Family.class, MediaType.APPLICATION_JSON);
        String xml = Marshaller.marshall(family, MediaType.APPLICATION_XML);
        String json = Marshaller.marshall(family, MediaType.APPLICATION_JSON);
        log.info(xml);
        log.info(json);

        Family xmlToObj = (Family) Marshaller.unMarshall(xml, Family.class, MediaType.APPLICATION_XML);
        Family jsonToObj = (Family) Marshaller.unMarshall(json, Family.class, MediaType.APPLICATION_JSON);

        xml = Marshaller.marshall(xmlToObj, MediaType.APPLICATION_XML);
        json = Marshaller.marshall(jsonToObj, MediaType.APPLICATION_JSON);
        log.info(xml);
        log.info(json);
    }

    @Autowired
    HttpClient httpClient;

    @Test
    public void postTest() throws Exception {
        String s  = "{\n" +
                "  \"address\": \"string\",\n" +
                "  \"child\": [\n" +
                "    {\n" +
                "      \"additionalInfo\": {\n" +
                "        \"favorite\": \"string\",\n" +
                "        \"hobby\": \"string\"\n" +
                "      },\n" +
                "      \"age\": \"string\",\n" +
                "      \"name\": \"string\",\n" +
                "      \"sex\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"daddy\": {\n" +
                "    \"additionalInfo\": {\n" +
                "      \"favorite\": \"string\",\n" +
                "      \"hobby\": \"string\"\n" +
                "    },\n" +
                "    \"age\": 0,\n" +
                "    \"name\": \"string\",\n" +
                "    \"sex\": \"string\"\n" +
                "  },\n" +
                "  \"familyName\": \"string\",\n" +
                "  \"mom\": {\n" +
                "    \"additionalInfo\": {\n" +
                "      \"favorite\": \"string\",\n" +
                "      \"hobby\": \"string\"\n" +
                "    },\n" +
                "    \"age\": 0,\n" +
                "    \"name\": \"string\",\n" +
                "    \"sex\": \"string\"\n" +
                "  }\n" +
                "}";

        try {
            Family family = (Family) Marshaller.unMarshall(s, Family.class, MediaType.APPLICATION_JSON);

//            OkHttpClientBuilder okHttpClientBuilder = new OkHttpClientBuilder.Builder()
//                    .setURL("http://localhost/test/echo")
//                    .addHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
//                    .addHeader("accept", MediaType.APPLICATION_JSON_VALUE)
//                    .setCONNECTION_TIME_OUT(3000)
//                    .setREAD_TIME_OUT(3000)
//                    .build();
//
//            httpClient.build(okHttpClientBuilder);
//
//            log.info(s);
//            log.info(httpClient.post(family, Family.class, MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON) + "");

        } catch (Exception e) {
            LogUtil u = new LogUtil();
            log.error(u.catchLog(e));
        }
    }
}
