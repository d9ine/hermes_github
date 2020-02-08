package com.interpark.hermes.controller;

import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.Child;
import com.interpark.hermes.models.SubClass;
import com.interpark.hermes.models.TestVO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Api(tags = {"TestController"})
@RequestMapping(value="/test")
public class TestController {
    @PostMapping(value = "/hello",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public TestVO hello(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                        @RequestBody TestVO body) throws Exception {

        TestVO vo = new TestVO();
        vo.setR("r");
        vo.setE("e");
        vo.setS("s");
        vo.setT("t");

        List<Child> childList = new ArrayList<>();

        Child child1 = new Child();
        child1.setAge(10);

        Child child2 = new Child();
        child2.setAge(11);

        childList.add(child1);
        childList.add(child2);
        vo.setChild(childList);

        SubClass sub = new SubClass();
        sub.setAa("aa");
        sub.setBb("bb");

        child1.setSubClass(sub);
        child2.setSubClass(sub);
        log.info(vo.toString());

        return body;
    }

    public static void main(String[] args) throws Exception {
        String s  = "{\n" +
                "  \"ab\": \"string\",\n" +
                "  \"child\": [\n" +
                "    {\n" +
                "      \"age\": 0,\n" +
                "      \"name\": \"string\",\n" +
                "      \"subClass\": {\n" +
                "        \"aa\": \"string\",\n" +
                "        \"bb\": \"string\"\n" +
                "      }\n" +
                "    },{\n" +
                "      \"age\": 1,\n" +
                "      \"name\": \"asd\",\n" +
                "      \"subClass\": {\n" +
                "        \"aa\": \"aa\",\n" +
                "        \"bb\": \"bb\"\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"e\": \"string\",\n" +
                "  \"s\": \"string\",\n" +
                "  \"t\": \"string\"\n" +
                "}";
        Marshaller m = new Marshaller();

        TestVO body = (TestVO) m.unMarshall(s, TestVO.class, Marshaller.MEDIA_TYPE.JSON);
        String xml = m.marshall(body, Marshaller.MEDIA_TYPE.XML);
        String json = m.marshall(body, Marshaller.MEDIA_TYPE.JSON);
        log.info(xml);
        log.info(json);

        TestVO xmlToObj = (TestVO) m.unMarshall(xml, TestVO.class, Marshaller.MEDIA_TYPE.XML);
        TestVO jsonToObj = (TestVO) m.unMarshall(json, TestVO.class, Marshaller.MEDIA_TYPE.JSON);

        xml = m.marshall(xmlToObj, Marshaller.MEDIA_TYPE.XML);
        json = m.marshall(jsonToObj, Marshaller.MEDIA_TYPE.JSON);
        log.info(xml);
        log.info(json);
    }
}
