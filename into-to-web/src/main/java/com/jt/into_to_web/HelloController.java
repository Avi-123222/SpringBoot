package com.jt.into_to_web;

import java.io.PrintWriter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    @RequestMapping({"/","/home"})
    public String home(){
        System.out.println("This is home page");
        return "home";

    }
    
    @RequestMapping({"/contact"})
    public String contact(Model model){
        model.addAttribute("name", "Java Technocrat");
        model.addAttribute("branch", "Java with SpringBoot");
        model.addAttribute("faculty", "Mr. Sai");
        System.out.println("Hi everyone!!!!");
        return "contact";

    } 



}
