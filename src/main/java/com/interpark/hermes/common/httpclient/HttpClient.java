package com.interpark.hermes.common.httpclient;

import com.interpark.hermes.aspect.MarshallingTarget;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Scope("prototype")
@Component(value="HttpClient")
public class HttpClient {//인스턴스가 리셋되면 Aop에서 자동탈락;;
    private final Integer DEFAULT_READ_TIME_OUT = 30000;
    private final Integer DEFAULT_CONNECTION_TIME_OUT = 10000;

    private Integer READ_TIME_OUT;
    private Integer CONNECTION_TIME_OUT;
    private String URL;
    private List<Header> header;

    public void build(OkHttpClientBuilder builder) {
        this.READ_TIME_OUT = builder.READ_TIME_OUT;
        this.CONNECTION_TIME_OUT = builder.CONNECTION_TIME_OUT;
        this.URL = builder.URL;
        this.header = builder.header;

        if(this.READ_TIME_OUT == null || this.READ_TIME_OUT == 0) {
            READ_TIME_OUT = DEFAULT_READ_TIME_OUT;
        }

        if(this.CONNECTION_TIME_OUT == null || this.CONNECTION_TIME_OUT == 0) {
            CONNECTION_TIME_OUT = DEFAULT_CONNECTION_TIME_OUT;
        }

        if(this.header == null) { // to avoid null point exception
            this.header = new ArrayList<>();
        }
    }

    public Object post(Object messageObject, Class<?> responseClazz) throws Exception {
        return post(messageObject, responseClazz,
                org.springframework.http.MediaType.APPLICATION_JSON,
                org.springframework.http.MediaType.APPLICATION_JSON);
    }

    /**
     * @param messageObject 전송할 객체
     * @param responseClazz 응답받을 객체 모델.class, Aspect 에서 사용됨
     * @param requestMediaType 전송할 객체의 타입(json or xml) Aspect 에서 사용됨
     * @param responseMediaType 응답받을 객체의 타입(json or xml) Aspect 에서 사용됨
     * @return 최종응답은 Aspect 에서 객처로 바인딩
     */
    @MarshallingTarget
    public Object post(Object messageObject, Class<?> responseClazz,
                       org.springframework.http.MediaType requestMediaType,
                       org.springframework.http.MediaType responseMediaType) throws Exception {
        RequestBody requestBody = RequestBody.create(((String) messageObject).getBytes());
        return execute(requestBody);
    }

    private String execute(RequestBody requestBody) throws Exception {
        Request.Builder builder = new Request.Builder();
        builder.url(this.URL);

        if(requestBody != null) {
            builder.post(requestBody);
        }

        for(Header header : this.header) {
            builder.addHeader(header.getKey(), header.getValue());
        }

        Request request = builder.build();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(this.CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(this.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        if(response.body() != null) {
            return response.body().toString();
        }

        return null;
    }
}
