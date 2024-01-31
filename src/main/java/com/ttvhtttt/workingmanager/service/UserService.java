package com.ttvhtttt.workingmanager.service;

import com.ttvhtttt.workingmanager.entity.User;
import com.ttvhtttt.workingmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    public List<User> listAll(){
        return repository.findAll();
    }
    public User findUserById(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }
    public int checkLogin(String username, String password){
        if (repository.findUserByUsernameAndPassword(username, password).getUsername().isEmpty()){
            return -1;
        }else {
            return repository.findUserByUsernameAndPassword(username, password).getUserID();
        }
    }
    public void register(String username, String password, String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPermission(2);
        repository.save(user);
    }
}
