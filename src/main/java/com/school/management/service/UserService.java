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
    	final List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User findByEmail(final String email) {
    	final User user=userRepository.findByEmail(email).orElseThrow(() -> new BadRequestServiceAlertException(400,"User not found with email: " + email));
        return user;
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }
}