package com.interpark.hermes.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

public class Marshaller {
    private static final HashMap<Class<?>, JAXBContext> jaxBContextMap = new HashMap<>();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * JAXBContext는 재사용이 가능(thread-safe)하고 무거우므로(GC overhead), 바인딩 할 객체별로 저장해놓고 재사용
     * @param clazz JAXBContext를 생성할 클래스
     * @return xml을 객체에 바인딩 할 JAXBContext
     * @throws JAXBException
     */
    private static JAXBContext getJaxbContextMap(Class<?> clazz) throws JAXBException {
        JAXBContext jaxbContext = jaxBContextMap.get(clazz);

        if(jaxbContext == null) {
            jaxbContext = JAXBContext.newInstance(clazz);
            setJaxBContextMap(clazz, jaxbContext);
        }

        return jaxbContext;
    }

    private synchronized static void setJaxBContextMap(Class<?> clazz, JAXBContext jaxbContext) {
        jaxBContextMap.put(clazz, jaxbContext);
    }

    public static String marshall(Object obj, MediaType mediaType) throws Exception {
        if(MediaType.APPLICATION_XML.equals(mediaType)) {
            JAXBContext jaxbContext = getJaxbContextMap(obj.getClass());

            javax.xml.bind.Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            marshaller.marshal(obj, sw);
            return sw.toString();

        } else {
            return objectMapper.writeValueAsString(obj);
        }
    }

    public static Object unMarshall(String str, Class<?> clazz, MediaType mediaType) throws Exception {
        if(MediaType.APPLICATION_XML.equals(mediaType)) {
            JAXBContext jaxbContext = getJaxbContextMap(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(str);

            return unmarshaller.unmarshal(reader);

        } else {
            return objectMapper.readValue(str, clazz);
        }
    }
}