package com.botanical.gardens.serverside.repositories.jpa;

import com.botanical.gardens.serverside.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
}
