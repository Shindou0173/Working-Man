package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.entity.User;
import com.ttvhtttt.workingmanager.service.AssignService;
import com.ttvhtttt.workingmanager.service.EmailService;
import com.ttvhtttt.workingmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assign")
public class AssignController {
    @Autowired
    private AssignService service;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

//    Get all tasks assigned to me
    @GetMapping("/myTask")
    public ResponseEntity findAllByTaskRecipient(@CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findAllByTaskRecipient(userID));
    }

    //    Get all task that created by me
    @GetMapping("/created")
    public ResponseEntity findAllByTaskGiver(@CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findAllByTaskGiver(userID));
    }

//    Task Giver giving a task to Task Recipient
@PostMapping("/giving")
public ResponseEntity giving(@CookieValue(name = "user") String userID, @RequestBody Map<String, String> formData){
    service.give(userID, formData.get("taskRecipient"), formData.get("taskID"));

    User taskRecipient = userService.findUserByUsername(formData.get("taskRecipient"));
    System.out.println(taskRecipient);
    if(taskRecipient != null){
        emailService.sendMessage(taskRecipient.getEmail(), "Nhiệm vụ mới", userService.findUserById(userID).getUsername()+" đã giao nhiệm vụ mới cho bạn, vui lòng kiểm tra!");
    }
    return ResponseEntity.ok("");
}

//    Who assigned this task to me
    @GetMapping("/who/{taskID}")
    public ResponseEntity who(@PathVariable String taskID, @CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findByTaskIdAndTaskRecipient(taskID, userID));
    }
//    Who do this task
    @GetMapping("/recipient/{taskID}")
    public ResponseEntity giver(@PathVariable String taskID, @CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findByTaskGiverAndTaskID(userID, taskID).getTaskRecipient());
    }
}

