package com.ttvhtttt.workingmanager.service;

import com.ttvhtttt.workingmanager.entity.Assign;
import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.entity.User;
import com.ttvhtttt.workingmanager.repository.AssignRepository;
import com.ttvhtttt.workingmanager.repository.TaskRepository;
import com.ttvhtttt.workingmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CookieValue;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssignService {
    @Autowired
    private AssignRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    public void give(String taskGiverid, String taskRecipientid, String taskID){
        User taskGiver = userRepository.findById(Integer.parseInt(taskGiverid)).get();
        Task task = taskRepository.findById(Integer.parseInt(taskID)).get();
        String[] taskRecipients = taskRecipientid.split(", ");
        Assign assignRequest = new Assign();
        for(String taskReci : taskRecipients){
            User taskRecipient = userRepository.findUserByUsername(taskReci);
            System.out.println(taskReci);
            assignRequest.setTaskGiver(taskGiver);
            assignRequest.setTaskID(task);
            assignRequest.setTaskRecipient(taskRecipient);
            repository.save(assignRequest);
        }
    }
    public List<Assign> findAllByTaskRecipient(@CookieValue(name = "user") String userID){
        User curr = userRepository.findById(Integer.parseInt(userID)).get();
        return repository.findAllByTaskRecipient(curr);
    }
    public List<Assign> findAllByTaskGiver(@CookieValue(name = "user") String userID){
        User curr = userRepository.findById(Integer.parseInt(userID)).get();
        List<Assign> list = repository.findAllByTaskGiver(curr);
        return list;
    }
    public String whoGiveMeThis(String taskID, String taskRecipient){
        Task task = taskRepository.findById(Integer.parseInt(taskID)).get();
        User user = userRepository.findById(Integer.parseInt(taskRecipient)).get();
        return repository.findByTaskRecipientAndTaskID(user, task).getTaskGiver().getUsername();
    }
    public List<Assign> statistic(List<Task> list, String specUser){
        List<Assign> result = new ArrayList<>();
        for(Task task: list){
            List<Assign> handle = repository.findAllByTaskID(task);
//            Remove midman
            Assign last = handle.get(0);
            for(Assign assign : handle){
                if(last.getTaskRecipient() == assign.getTaskGiver()){
                    last.setTaskRecipient(assign.getTaskRecipient());
                }
            }
//            Statictic by username
            if(specUser.equals("")){
                result.add(last);
            }else {
                if(last.getTaskRecipient().getUsername().equals(specUser)){
                    result.add(last);
                }
            }
        }
        return result;
    }
    public Assign findByTaskGiverAndTaskID(String taskGiver, String taskID){
        return repository.findByTaskGiverAndTaskID(userRepository.findById(Integer.parseInt(taskGiver)).get(), taskRepository.findById(Integer.parseInt(taskID)).get());
    }
}
