package com.jt.hello;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class AppConfig {
    @Bean
    Teacher Teacher(){
        return new Teacher();
    }
//we should only use @Bean to provide the object of the predefined class to Spring Container(so the it can use custom behaviour of that object instead of predefined behaviour)
   
       
}
