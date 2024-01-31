package com.ttvhtttt.workingmanager.service;

import com.ttvhtttt.workingmanager.entity.Issues;
import com.ttvhtttt.workingmanager.repository.IssueRepository;
import com.ttvhtttt.workingmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    @Autowired
    private IssueRepository repository;
    @Autowired
    private TaskRepository taskRepository;
    public void newIssue(String issueName, String issueDes, String taskID){
        Issues issues = new Issues();
        issues.setIssuesName(issueName);
        issues.setDescription(issueDes);
        issues.setTaskID(taskRepository.findById(Integer.parseInt(taskID)).get());
        repository.save(issues);
    }
    public Issues findByTaskID(String taskID){
        return repository.findByTaskID(taskRepository.findById(Integer.parseInt(taskID)).get());
    }
}
