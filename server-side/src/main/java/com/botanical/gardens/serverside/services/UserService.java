package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.entities.User;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public interface UserService {
    User saveUser(User user) throws OWLOntologyCreationException, OWLOntologyStorageException;
    User getUserById(Long userId);
    User getUserByEmail(String email);
}
