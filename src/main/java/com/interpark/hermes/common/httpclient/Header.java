package com.interpark.hermes.common.httpclient;

import lombok.Getter;

@Getter
public class Header {
    private String key;
    private String value;

    public Header(Builder builder) {
        this.key = builder.key;
        this.value = builder.value;
    }

    public static class Builder {
        private String key;
        private String value;

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Header build() {
            return new Header(this);
        }
    }
}
