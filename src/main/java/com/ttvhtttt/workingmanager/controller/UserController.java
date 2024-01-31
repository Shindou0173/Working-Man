package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/all")
    public ResponseEntity listAll(){
        return ResponseEntity.ok(service.listAll());
    }
    @GetMapping("/currUser")
    public ResponseEntity currUser(@CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findUserById(userID));
    }
    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody Map<String, String> formData, HttpServletResponse response){
        if(service.checkLogin(formData.get("username"), formData.get("password")) == -1){
            System.out.println("id la "+service.checkLogin(formData.get("username"), formData.get("password")));
            return ResponseEntity.badRequest().build();
        }else {
            Cookie cookie = new Cookie("user", Integer.toString(service.checkLogin(formData.get("username"), formData.get("password"))));
            cookie.setPath("/");
            cookie.setMaxAge(36000);
            response.addCookie(cookie);
            return ResponseEntity.ok("Đăng nhập thành công");
        }
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Map<String, String> formData){
        service.register(formData.get("username"), formData.get("password"), formData.get("email"));
        return ResponseEntity.ok("Đăng kí thành công!");
    }
}