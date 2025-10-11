package com.jt.hello;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")// by default scope is singleton or we can say by default every bean is created by the Spring Container is singleton bean
@Component
public class Student {
    public void sayHello(){
        System.out.println("Hello from Student class");
    }
    
}
