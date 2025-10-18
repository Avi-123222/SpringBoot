package com.jt.into_to_web;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



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
    @RequestMapping({"/form"})
    public String form(){
        System.out.println("This is form page");
        return "form";

    }
    //@RequestMapping(path= "/submit", method = RequestMethod.POST)
    @PostMapping("/submit")
    
   
    
    // public String submitForm(@RequestParam(name="user" ,defaultValue="Java Technocrat") String username, @RequestParam("password") String password , Model model){
    public String submit1(@ModelAttribute LoginCredential credential ,Model model){
        System.out.println("This is submit page");
      
        model.addAttribute("name", credential.getUsername());
        model.addAttribute("pass", credential.getPassword());
        return "details";


    }
    @GetMapping("/multi-submit")
    public String submit2(Model model) {
        LoginCredential credential1 = new LoginCredential("JT", "1234");
         LoginCredential credential2 = new LoginCredential("JavaTechnocrat", "5678");
         LoginCredential credential3 = new LoginCredential("Avishek", "3456");
      model.addAttribute("credentials", List.of(credential1, credential2, credential3));
      return "credentials";
    }
    



}
