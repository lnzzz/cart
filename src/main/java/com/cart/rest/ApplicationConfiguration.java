package com.cart.rest;
 
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
@Configuration
@EnableWebMvc
@EnableScheduling
@ComponentScan(basePackages = "com.cart.rest")
public class ApplicationConfiguration {
     
 
}