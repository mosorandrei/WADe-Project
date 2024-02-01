package com.botanical.gardens.serverside.repositories;

import com.botanical.gardens.serverside.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
