package com.ttvhtttt.workingmanager.repository;

import com.ttvhtttt.workingmanager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    @Query("SELECT u FROM User u WHERE u.permission < :maxPermissionLevel")
    List<User> findUsersWithPermissionLevelLowerThan(int maxPermissionLevel);
}
