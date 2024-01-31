package com.ttvhtttt.workingmanager.repository;

import com.ttvhtttt.workingmanager.entity.Issues;
import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issues, Integer> {
    Issues findByTaskID(Task taskID);
}
