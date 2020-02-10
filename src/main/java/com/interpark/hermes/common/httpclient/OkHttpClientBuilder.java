package com.interpark.hermes.common.httpclient;

import java.util.ArrayList;
import java.util.List;

public class OkHttpClientBuilder {
    public Integer READ_TIME_OUT;
    public Integer CONNECTION_TIME_OUT;
    public String URL;
    public List<Header> header;

    public OkHttpClientBuilder(Builder builder) {
        this.URL = builder.URL;
        this.READ_TIME_OUT = builder.READ_TIME_OUT;
        this.CONNECTION_TIME_OUT = builder.CONNECTION_TIME_OUT;
        this.header = builder.header;
    }

    public static class Builder {
        private String URL;
        private Integer READ_TIME_OUT;
        private Integer CONNECTION_TIME_OUT;
        private List<Header> header;

        public Builder setURL(String URL) {
            this.URL = URL;
            return this;
        }

        public Builder setREAD_TIME_OUT(Integer READ_TIME_OUT) {
            this.READ_TIME_OUT = READ_TIME_OUT;
            return this;
        }

        public Builder setCONNECTION_TIME_OUT(Integer CONNECTION_TIME_OUT) {
            this.CONNECTION_TIME_OUT = CONNECTION_TIME_OUT;
            return this;
        }

        public Builder addHeader(String key, String value) {
            if(this.header == null) {
                header = new ArrayList<>();
            }

            Header header = new Header.Builder().setKey(key).setValue(value).build();

            this.header.add(header);
            return this;
        }

        public OkHttpClientBuilder build() {
            return new OkHttpClientBuilder(this);
        }
    }
}
