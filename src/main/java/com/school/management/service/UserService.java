package com.school.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.management.entities.User;
import com.school.management.exception.BadRequestServiceAlertException;
import com.school.management.repository.UserRepository;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User findByEmail(String email) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
    	User user=userRepository.findByEmail(email).orElseThrow(() -> new BadRequestServiceAlertException("User not found with email: " + email));
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}