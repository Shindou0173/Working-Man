package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assign")
public class AssignController {
    @Autowired
    private AssignService service;

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
        return ResponseEntity.ok("");
    }

//    Who assigned this task to me
    @GetMapping("/who/{taskID}")
    public ResponseEntity who(@PathVariable String taskID, @CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.whoGiveMeThis(taskID, userID));
    }
//    Who do this task
    @GetMapping("/recipient/{taskID}")
    public ResponseEntity giver(@PathVariable String taskID, @CookieValue(name = "user") String userID){
        return ResponseEntity.ok(service.findByTaskGiverAndTaskID(userID, taskID).getTaskRecipient());
    }
}

