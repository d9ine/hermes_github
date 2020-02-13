package com.interpark.hermes.config;

import com.interpark.hermes.interceptor.Interceptor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import javax.sql.DataSource;

@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.interpark.hermes.*"})
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);

        registry.addInterceptor(new Interceptor()).addPathPatterns("/test/**")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/*/api-docs/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public HttpMessageConverters httpMessageConverters() {
        HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new MappingJackson2HttpMessageConverter());
        return httpMessageConverters;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() {
        DataSource dataSource = new HikariDataSource(hikariConfig());
        return dataSource;
    }

//    private ApplicationContext applicationContext;
//
//    @Autowired
//    public WebMvcConfig(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    public class DatabaseConfiguration {
//        @Bean
//        public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
//            SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            sqlSessionFactoryBean.setDataSource(dataSource);
//            sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/resources/mapper/mysql/*.xml"));
//            return sqlSessionFactoryBean.getObject();
//        }
//
//        @Bean
//        public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//            return new SqlSessionTemplate(sqlSessionFactory);
//        }
//    }

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(createXmlHttpMessageConverter());
//    }
//
//    @Bean
//    public HttpMessageConverter<Object> createXmlHttpMessageConverter() {
//        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
//
//        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
//        marshallingHttpMessageConverter.setMarshaller(xStreamMarshaller);
//        marshallingHttpMessageConverter.setUnmarshaller(xStreamMarshaller);
//
//        return marshallingHttpMessageConverter;
//    }
}
