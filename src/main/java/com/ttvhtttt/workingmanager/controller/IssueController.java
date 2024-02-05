package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    private IssueService service;
    @PostMapping("/new")
    public ResponseEntity newIssue(@RequestBody Map<String, String> formData){
        service.newIssue(formData.get("issueDescription"), formData.get("taskID"));
        return ResponseEntity.ok("");
    }
    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Map<String, String> formData){
        service.update(formData.get("issueDescription"), formData.get("taskID"));
        return ResponseEntity.ok("");
    }
    @GetMapping("/find/{taskID}")
    public ResponseEntity find(@PathVariable String taskID){
        return ResponseEntity.ok(service.findByTaskID(taskID));
    }
}
