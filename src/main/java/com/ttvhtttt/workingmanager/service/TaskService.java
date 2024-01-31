package com.ttvhtttt.workingmanager.service;

import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.entity.User;
import com.ttvhtttt.workingmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;

    public Task save(String taskDes, String deadline){
        Task newTask = new Task();
        newTask.setTaskDescription(taskDes);
        LocalDate dl = LocalDate.parse(deadline);
//        Way to get current date
        LocalDateTime today = LocalDateTime.now();
//        Format date to the format that we want
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        newTask.setDeadline(dl.format(formatter));
//        And set it as start day of the task
        newTask.setStartDate(today.format(formatter));
        newTask.setStatus("Đã tiếp nhận");
        return repository.save(newTask);
    }

    public void subtask(String taskDes, String deadline, String subtask){
        Task newTask = new Task();
        newTask.setTaskDescription(taskDes);
        newTask.setDeadline(deadline);
        newTask.setDivinefrom(Integer.parseInt(subtask));
//        Way to get current date
        LocalDateTime today = LocalDateTime.now();
//        Format date to the format that we want
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        And set it as start day of the task
        newTask.setStartDate(today.format(formatter));
        newTask.setStatus("Đã tiếp nhận");
        repository.save(newTask);
    }

    public Task findTaskById(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }

    public List<Task> statistic(String startDate, String endDate){
        List<Task> allTasks = repository.findAll();
        List<Task> filteredTasks = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDateParsed = LocalDate.parse(startDate, formatter);
        LocalDate endDateParsed = LocalDate.parse(endDate, formatter);

        for (Task task : allTasks) {
            LocalDate taskDeadline = LocalDate.parse(task.getDeadline(), formatter);
            if (taskDeadline.isEqual(startDateParsed) || (taskDeadline.isAfter(startDateParsed) && taskDeadline.isBefore(endDateParsed))) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public void update(int taskID, String status){
        Task task = repository.findById(taskID).get();
        task.setStatus(status);
        repository.save(task);
    }

    public Task findById(String taskID){
        return repository.findById(Integer.parseInt(taskID)).get();
    }
    public void evaluate(Task task){
        repository.save(task);
    }
}
