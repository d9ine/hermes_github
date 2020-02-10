package com.interpark.hermes;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.Family;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/** 통합테스트 시작시 모든 리소스(db커넥션등) 로드됨 **/

@Slf4j
@SpringBootTest
class HermesApplicationTests {
    @Test
    void marshallSample() throws Exception {
        String s  = "{" +
                "  \"address\": \"seoul\"," +
                "  \"child\": [" +
                "    {" +
                "      \"additional\": {" +
                "        \"favorite\": \"icecreem\"," +
                "        \"hobby\": \"game\"" +
                "      }," +
                "      \"age\": 6," +
                "      \"name\": \"sister\"," +
                "      \"sex\": \"femail\"" +
                "    },{" +
                "      \"additional\": {" +
                "        \"favorite\": \"candy\"," +
                "        \"hobby\": \"watching tv\"" +
                "      }," +
                "      \"age\": 12," +
                "      \"name\": \"brother\"," +
                "      \"sex\": \"male\"" +
                "    }" +
                "  ]," +
                "  \"daddy\": {" +
                "    \"additionalInfo\": {" +
                "      \"favorite\": \"sleep\"," +
                "      \"hobby\": \"fishing\"" +
                "    }," +
                "    \"age\": 40," +
                "    \"name\": \"daddy\"," +
                "    \"sex\": \"male\"" +
                "  }," +
                "  \"familyName\": \"family\"," +
                "  \"mom\": {" +
                "    \"additionalInfo\": {" +
                "      \"favorite\": \"wine\"," +
                "      \"hobby\": \"cooking\"" +
                "    }," +
                "    \"age\": 35," +
                "    \"name\": \"mommy\"," +
                "    \"sex\": \"femail\"" +
                "  }" +
                "}";
        Marshaller m = new Marshaller();

        Family family = (Family) m.unMarshall(s, Family.class, Marshaller.MEDIA_TYPE.JSON);
        String xml = m.marshall(family, Marshaller.MEDIA_TYPE.XML);
        String json = m.marshall(family, Marshaller.MEDIA_TYPE.JSON);
        log.info(xml);
        log.info(json);

        Family xmlToObj = (Family) m.unMarshall(xml, Family.class, Marshaller.MEDIA_TYPE.XML);
        Family jsonToObj = (Family) m.unMarshall(json, Family.class, Marshaller.MEDIA_TYPE.JSON);

        xml = m.marshall(xmlToObj, Marshaller.MEDIA_TYPE.XML);
        json = m.marshall(jsonToObj, Marshaller.MEDIA_TYPE.JSON);
        log.info(xml);
        log.info(json);
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
}
