package com.interpark.hermes.common;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

@Slf4j
public class Marshaller {
    public enum MEDIA_TYPE {
        XML, JSON
    }

    public String marshall(Object obj, MEDIA_TYPE media_type) throws Exception {
        if(MEDIA_TYPE.XML.equals(media_type)) {
            return ObjectToXml(obj);

        } else {
            //여기 gson --> jackson으로 변경할지말지 고민해보기
            return new Gson().toJson(obj);
        }
    }

    public Object unMarshall(String str, Class<?> clazz, MEDIA_TYPE media_type) throws Exception {
        if(MEDIA_TYPE.XML.equals(media_type)) {
            return xmlToObject(clazz, str);

        } else {
            //여기 gson --> jackson으로 변경할지말지 고민해보기
            return new Gson().fromJson(str, clazz);
        }
    }

    private String ObjectToXml(Object obj) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());

        javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);

        return sw.toString();
    }

    private Object xmlToObject(Class<?> clazz, String str) throws Exception {
        JAXBContext jaxBContext = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = jaxBContext.createUnmarshaller();
        StringReader reader = new StringReader(str);

        return unmarshaller.unmarshal(reader);
    }
}
