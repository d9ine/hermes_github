package com.interpark.hermes.common.httpclient;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component(value="HttpClient")
@Scope("prototype")
public class HttpClient {//인스턴스가 리셋되면 Aop에서 자동탈락;;
    private final Integer DEFAULT_READ_TIME_OUT = 30000;
    private final Integer DEFAULT_CONNECTION_TIME_OUT = 10000;

    private Integer READ_TIME_OUT;
    private Integer CONNECTION_TIME_OUT;
    private String URL;
    private List<Header> header;

    private OkHttpClient okHttpClient;
    private Request request;

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

    public String get() throws Exception {
        return execute(null);
    }

    @MarshallingTarget
    public Object post(Object messageObject, Class clazz) throws Exception {
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

        request = builder.build();

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(this.CONNECTION_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(this.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(false)
                .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

/*    public static class Builder {
        private Integer READ_TIME_OUT;
        private Integer CONNECTION_TIME_OUT;
//        private String URL;
        private String message;
        private List<Header> header;

//        public Builder(String URL) throws Exception {
//            setURL(URL);
//        }

//        private void setURL(String URL) throws Exception {
//            this.URL = URL;
//
//            if(URL == null || "".equals(URL)) {
//                throw new Exception("URL is necessary");
//            }
//        }

        public Builder setREAD_TIME_OUT(Integer READ_TIME_OUT) {
            this.READ_TIME_OUT = READ_TIME_OUT;
            return this;
        }

        public Builder setCONNECTION_TIME_OUT(Integer CONNECTION_TIME_OUT) {
            this.CONNECTION_TIME_OUT = CONNECTION_TIME_OUT;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder addHeader(String key, String value) {
            if(this.header == null) {
                this.header = new ArrayList<>();
            }

            this.header.add(new Header.Builder().setKey(key).setValue(value).build());
            return this;
        }

        public HttpClient build() {
            return new HttpClient(this);
        }
    }*/
}
