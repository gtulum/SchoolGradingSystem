package com.school.SchoolGradingSystem.configuration;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
	
    private static final String BEARER_AUTH = "Bearer";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.school.SchoolGradingSystem"))
                .paths(PathSelectors.any())
                .build().apiInfo(info())
                .securitySchemes(securitySchemes())
				.securityContexts(Arrays.asList(securityContext()));
    }

    private ApiInfo info() {

        return new ApiInfo(
                "School Grading API",
                "School Grading API Description",
                "v1",
                "Terms of service",
                new Contact("Gulsah Tulum", "www.example.com", "gulsahoztunc@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
       
    }
    
    private List<SecurityScheme> securitySchemes() {
		return Arrays.asList(new ApiKey(BEARER_AUTH, "Authorization", "header"));
		
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(Arrays.asList(bearerAuthReference())).forPaths(PathSelectors.ant("/api/**")).build();
		
	}

	private SecurityReference bearerAuthReference() {
		return new SecurityReference(BEARER_AUTH, new AuthorizationScope[0]);
	}
   
   
    
}