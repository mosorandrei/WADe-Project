package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long userId);

    User getUserByEmail(String email);
}
