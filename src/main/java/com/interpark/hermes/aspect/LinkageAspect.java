package com.interpark.hermes.aspect;

import com.interpark.hermes.common.Marshaller;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LinkageAspect {
    @Around("@annotation(com.interpark.hermes.common.httpclient.AocTarget)")
//    @Around("execution(* com.interpark.hermes.common.httpclient.HttpClient.post())")
    public Object marshallAroundLinkage(ProceedingJoinPoint pjp) throws Throwable {
        log.info("여기오니?");
        log.info(pjp.getArgs().toString());
        Object object = pjp.getArgs();
        String message = null;

        if(object != null) {
            Marshaller marshaller = new Marshaller();
            message = marshaller.marshall(object, Marshaller.MEDIA_TYPE.JSON);
            log.info(message);
        }

        Object result = pjp.proceed(new Object[]{message});
        log.info((String) result);

        return result;
    }
}
