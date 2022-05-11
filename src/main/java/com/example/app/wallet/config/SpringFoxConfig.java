package com.example.app.wallet.config;

import com.example.app.wallet.WalletDemoApplication;
import com.example.app.wallet.config.annotation.DeveloperInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.*;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        /** we use reflection api to access annotations at runtime **/
        final Class<WalletDemoApplication> walletDemoApplicationClass = WalletDemoApplication.class;
        Annotation[] annotations = walletDemoApplicationClass.getAnnotations();
        DeveloperInfo developerInfo = null;

        for (Annotation annotation : annotations) {
            if(annotation instanceof DeveloperInfo){
                developerInfo = (DeveloperInfo) annotation;
            }
        }

        return new ApiInfo(
                "Wallet Service REST API",
                "Wallet Service REST API project",
                "1.0",
                "Terms of service",
                new Contact(developerInfo.createdBy(), developerInfo.url(), developerInfo.email()),
                //new Contact("Abkode", "https://github.com/abkaytan", "a.buyukkaytan@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
