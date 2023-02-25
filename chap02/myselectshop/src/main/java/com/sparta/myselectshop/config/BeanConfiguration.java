package com.sparta.myselectshop.config;

import com.sparta.myselectshop.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProductRepository productRepository() {
        String dbUrl = "jdbc:h2:mem:db";
        String username = "sa";
        String password = "";
        return new ProductRepository(dbUrl, username, password);
    }
}
/*
@component 애노테이션 사용하지 않고, Spring Bean(IoC) 등록하는 방법
 */