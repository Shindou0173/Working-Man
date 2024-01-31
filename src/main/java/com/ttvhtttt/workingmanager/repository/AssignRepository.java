package com.ttvhtttt.workingmanager.repository;

import com.ttvhtttt.workingmanager.entity.Assign;
import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignRepository extends JpaRepository<Assign, Integer> {
    List<Assign> findAllByTaskRecipient(User taskRecipient);
    List<Assign> findAllByTaskGiver(User taskGiver);
    List<Assign> findAllByTaskID(Task taskID);
    Assign findByTaskID(Task taskID);
    Assign findByTaskGiverAndTaskID(User taskGiver, Task taskID);
    Assign findByTaskRecipientAndTaskID(User taskRecipient, Task taskID);
}
