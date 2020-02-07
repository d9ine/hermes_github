package com.interpark.hermes.controller;

import com.google.gson.Gson;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.ChildClass;
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
        vo.setE("E");
        vo.setS("S");
        vo.setT("T");
        vo.setTt("tt");

        List<ChildClass> childList = new ArrayList<ChildClass>();
        vo.setChildList(childList);

        ChildClass child1 = new ChildClass();
        child1.setAge(10);

        ChildClass child2 = new ChildClass();
        child2.setAge(11);

        childList.add(child1);
        childList.add(child2);

        return vo;
//        return str;
    }

    public static void main(String[] args) throws Exception {
        String s = "{\"e\":\"e\",\"s\":\"1\",\"tt\":\"t\",\"subClass\":{\"aa\":\"aa\",\"bb\":\"bb\"},\"subClass2\":{\"aa\":\"aa\",\"bb\":\"bb\"},\"childList\":[{\"age\":10},{\"age\":11}]}";
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
