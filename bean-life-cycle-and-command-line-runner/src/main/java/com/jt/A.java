package com.jt;

import org.springframework.stereotype.Component;

@Component

public class A {
    
    public A(){
        System.out.println("A is instantiated");
    }
    public void show(){
        System.out.println("Inside show method of A");
    }
}
