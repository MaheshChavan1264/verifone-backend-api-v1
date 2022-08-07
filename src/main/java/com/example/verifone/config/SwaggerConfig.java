package com.example.verifone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .build();
    }

    private ApiInfo getInfo() {

        return new ApiInfo("Verifone Backend Application",
                "This project is developed by Mahesh Chavan",
                "1.0",
                "Terms of Service",
                new Contact("Mahesh", "https://react-portfolio-delta-ruby.vercel.app/", "mahesh.chavan1264@gmail.com"),
                "License of APIs", "API License Url", Collections.emptyList()
        );
    }
}
