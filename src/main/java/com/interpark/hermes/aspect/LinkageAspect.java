package com.interpark.hermes.aspect;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.Family;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LinkageAspect extends LogUtil {
    @Around("@annotation(com.interpark.hermes.aspect.MarshallingTarget)")
    public Object marshallBeforeLinkage(ProceedingJoinPoint pjp) throws Throwable {
        Object parameter = pjp.getArgs()[0];

        Class<?> requestClass = (Class<?>) parameter.getClass();
        Class<?> returnClass = (Class<?>) pjp.getArgs()[1];

        MediaType requestMediaType = (MediaType) pjp.getArgs()[2];
        MediaType responseMediaType = (MediaType) pjp.getArgs()[3];

        String message = null;

        if(parameter != null) {
            Marshaller marshaller = new Marshaller();
            message = marshaller.marshall(parameter, requestMediaType);

            log.info("REQ : " + message);

            Object result = pjp.proceed(new Object[]{message, returnClass, requestMediaType, responseMediaType});

            log.info("RES : " + (String) result);

            result = marshaller.unMarshall((String) result, (Class) returnClass, responseMediaType);
            return result;

        } else {
            return null;
        }
    }
}
