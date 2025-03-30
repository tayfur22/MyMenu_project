package com.example.Mymenu.project.service.impl;

import com.example.Mymenu.project.entity.User;
import com.example.Mymenu.project.exception.EntityNotFoundException;
import com.example.Mymenu.project.exception.ResourceNotFoundException;
import com.example.Mymenu.project.repository.UserRepository;
import com.example.Mymenu.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
    }

    @Override
    public User update(User user, Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User with id " + id + " not found");

        }
        user.setId(id);
        return userRepository.save(user);

    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
    }

    @Override
    public User findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
    }
}
