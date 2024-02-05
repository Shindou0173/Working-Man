package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/report")
    public ResponseEntity sendEmail(@RequestBody Map<String, String> formData) {
        String to = formData.get("to");
        String subject = "Nhiệm vụ đã hoàn thành";
        String text = formData.get("user")+" đã hoàn thành nhiệm vụ được giao, vui lòng đánh giá kết quả nhiệm vụ!";
        emailService.sendMessage(to, subject, text);
        return ResponseEntity.ok("");
    }
    @PostMapping("/newTask")
    public ResponseEntity newTask(@RequestBody Map<String, String> formData){
        String to = formData.get("to");
        String subject = "Được giao nhiệm vụ mới";
        String text = formData.get("user")+" vừa giao cho bạn nhiệm vụ mới, vui lòng kiểm tra!";
        emailService.sendMessage(to, subject, text);
        return ResponseEntity.ok("");
    }
}
