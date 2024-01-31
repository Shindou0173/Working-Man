package com.ttvhtttt.workingmanager.repository;

import com.ttvhtttt.workingmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
}
