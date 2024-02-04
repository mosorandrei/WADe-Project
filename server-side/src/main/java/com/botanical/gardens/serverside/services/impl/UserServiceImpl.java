package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.User;
import com.botanical.gardens.serverside.repositories.jpa.UserRepository;
import com.botanical.gardens.serverside.repositories.owl.UserOwlRepository;
import com.botanical.gardens.serverside.services.UserService;
import lombok.Getter;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Getter
    private final UserRepository userRepository;

    private final UserOwlRepository userOwlRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserOwlRepository userOwlRepository) {
        this.userRepository = userRepository;
        this.userOwlRepository = userOwlRepository;
    }

    @Override
    public User saveUser(User user) throws OWLOntologyCreationException, OWLOntologyStorageException {
        User newUser = userRepository.save(user);
        userOwlRepository.addUserIndividual(newUser.getFirstName(), newUser.getLastName(), Math.toIntExact(newUser.getId()));
        return newUser;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException(String.format("Could not find user with the id: %s", userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException(String.format("Could not find user with the email: %s", email)));
    }
}
