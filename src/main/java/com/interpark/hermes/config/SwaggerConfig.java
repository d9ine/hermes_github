package com.interpark.hermes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("hermes API")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.interpark.hermes.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.docketInfo())
                .tags(    new Tag("TestController", "API")
            );
    }

    private ApiInfo docketInfo() {
        return new ApiInfoBuilder()
            .title("hermes API")
            .description("인터파크 자유여행플랫폼 인터페이스 API")
            .version("1.0")
            .build();
    }
}
