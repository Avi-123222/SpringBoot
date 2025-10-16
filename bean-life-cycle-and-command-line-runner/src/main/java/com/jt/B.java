package com.jt;

import org.springframework.stereotype.Component;

@Component

public class B {
    public B(){
        System.out.println("B is instantiated");
    }
     public void show1(){
        System.out.println("Inside show method of B");
    }
    
}
