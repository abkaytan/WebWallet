package com.example.app.wallet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomConfiguration implements WebMvcConfigurer {

    // ilgili application contexe eklemek istedğimiz java sınıflarının metodları için,
    // configurationlar sınıflara aynı işlemi yapar
    @Bean
    public CustomInterceptor getInterceptor() {
        return new CustomInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
    }
}
