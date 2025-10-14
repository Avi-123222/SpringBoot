package com.jt.into_to_web;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HelloController {
    @RequestMapping(path="/hello")
    public void sayHello(PrintWriter writer){

        System.out.println("Hello to web");
        writer.println("Hello to web in browser");

    }
    @RequestMapping(path="/login")
    public void login(PrintWriter writer){

        System.out.println("Hello to login page");
        writer.println("login successful");

    }



}
