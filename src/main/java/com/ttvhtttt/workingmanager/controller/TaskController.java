package com.ttvhtttt.workingmanager.controller;

import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.service.AssignService;
import com.ttvhtttt.workingmanager.service.IssueService;
import com.ttvhtttt.workingmanager.service.TaskService;
import com.ttvhtttt.workingmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService service;
    @Autowired
    private IssueService issueService;
    @Autowired
    private AssignService assignService;
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Map<String, String> formData, @CookieValue(name = "user") String userID){
        Task task = service.save(formData.get("taskDescription"), formData.get("deadline"));
        issueService.newIssue("Ghi chú", formData.get("issue"), Integer.toString(task.getTaskID()));
        assignService.give(userID, formData.get("taskRecipient"), Integer.toString(task.getTaskID()));
        return ResponseEntity.ok("");
    }
    @PostMapping("/subtask")
    public ResponseEntity subtask(@RequestBody Map<String, String> formData){
        service.subtask(formData.get("taskDes"), formData.get("deadline"), formData.get("divineFrom"));
        return ResponseEntity.ok("");
    }
    @PostMapping("/statistic")
    public ResponseEntity statistic(@RequestBody Map<String, String> formData, @CookieValue(name = "user") String userID){
        LocalDate start = LocalDate.parse(formData.get("startDate"));
        LocalDate end = LocalDate.parse(formData.get("endDate"));
        String specUser = formData.get("specUser");
        if(specUser.equals("Tất cả")){
            return ResponseEntity.ok(assignService.statistic(service.statistic(start.toString(), end.toString()), ""));
        }else {
            return ResponseEntity.ok(assignService.statistic(service.statistic(start.toString(), end.toString()), specUser));
        }
    }
    @PostMapping("/report")
    public ResponseEntity report(@RequestBody Map<String, String> formData){
        service.update(Integer.parseInt(formData.get("taskID")), formData.get("status"));
        return ResponseEntity.ok("Update status OK!!!");
    }
    @PostMapping("/assign")
    public ResponseEntity assign(@RequestBody Map<String, String> formData){
        return ResponseEntity.ok("");
    }
    @GetMapping("/find/{taskId}")
    public ResponseEntity find(@PathVariable String taskId){
        return ResponseEntity.ok(service.findById(taskId));
    }
    @PostMapping("/evaluate")
    public ResponseEntity evaluate(@RequestBody Map<String, String> formData){
        Task task = service.findById(formData.get("taskID"));
        task.setResult(formData.get("result"));
        service.evaluate(task);
        return ResponseEntity.ok("");
    }
}
