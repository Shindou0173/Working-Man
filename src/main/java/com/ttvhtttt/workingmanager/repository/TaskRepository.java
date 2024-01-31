package com.ttvhtttt.workingmanager.repository;

import com.ttvhtttt.workingmanager.entity.Task;
import com.ttvhtttt.workingmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
