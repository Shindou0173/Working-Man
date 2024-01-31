package com.ttvhtttt.workingmanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
    @GetMapping("/newtask")
    public String newtask(){
        return "newTask";
    }
    @GetMapping("assignTo/{taskID}")
    public String assignto(@PathVariable String taskID){ return "assign"; }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String regis(){
        return "register";
    }
    @GetMapping("/evaluate/{taskID}")
    public String eval(@PathVariable String taskID){ return "evaluate"; }
}
