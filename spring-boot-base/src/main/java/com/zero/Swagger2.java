package com.zero;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @Description:
 * @author: yezhaoxing
 * @date: 2017/5/9
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        /*
         * return new
         * Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
         * .apis(RequestHandlerSelectors.basePackage("com.zero.web.controller"))
         * .paths(PathSelectors.any()) .build();
         */
        StopWatch watch = new StopWatch();
        watch.start();
        Docket swaggerSpringMvcPlugin = new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .genericModelSubstitutes(ResponseEntity.class).select().paths(regex(".*?")).build();
        watch.stop();
        return swaggerSpringMvcPlugin;
    }

    private ApiInfo apiInfo() {
        /*
         * return new
         * ApiInfoBuilder().title("Spring Boot中使用Swagger2构建RESTful APIs").
         * description(null)
         * .termsOfServiceUrl(null).contact(null).version(null).build();
         */
        return new ApiInfoBuilder().title("spring-boot API").description(String.format("spring-boot API 说明书"))
                .termsOfServiceUrl(null).license("仅供内部参考").licenseUrl(null).build();
    }
}