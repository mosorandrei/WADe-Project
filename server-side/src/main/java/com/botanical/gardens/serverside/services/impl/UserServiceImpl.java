package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.User;
import com.botanical.gardens.serverside.repositories.UserRepository;
import com.botanical.gardens.serverside.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("Could not find user with the id: %s", userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("Could not find user with the email: %s", email)));
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
}
