package com.interpark.hermes.aspect;

import com.interpark.hermes.common.LogUtil;
import com.interpark.hermes.common.Marshaller;
import com.interpark.hermes.models.Family;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LinkageAspect extends LogUtil {
    @Around("@annotation(com.interpark.hermes.common.httpclient.MarshallingTarget)")
    public Object marshallBeforeLinkage(ProceedingJoinPoint pjp) throws Throwable {
        Object parameter = pjp.getArgs()[0];
        Object returnType = pjp.getArgs()[1];

        String message = null;

        if(parameter != null) {
            Marshaller marshaller = new Marshaller();
            message = marshaller.marshall(parameter, Marshaller.MEDIA_TYPE.JSON);

            log.info("REQ : " + message);

            Object result = pjp.proceed(new Object[]{message, returnType});

            log.info("RES : " + (String) result);

            result = marshaller.unMarshall((String) result, (Class) returnType, Marshaller.MEDIA_TYPE.JSON);
            return result;

        } else {
            return null;
        }
    }
}
