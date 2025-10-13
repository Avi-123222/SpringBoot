package com.jt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component

public class Greet {
    private Greeting greeting;
    public Greet(){
        System.out.println("Greet is instantiated");
    }  
    @Autowired 
        public void setGreeting(Greeting greeting){
             System.out.println("Dependency is injected");
            this.greeting=greeting;
           
        }
        @PostConstruct
        public void init(){
            System.out.println("This method is called after dependency injection");
        }
        public  void greet(){
            System.out.println("Hello Everyone");

        }
        @PreDestroy
        public void destroyGreet(){
            System.out.println("Greet bean destroyed");
        }
        
            
    }
    

