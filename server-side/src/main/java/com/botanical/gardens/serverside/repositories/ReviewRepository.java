package com.botanical.gardens.serverside.repositories;

import com.botanical.gardens.serverside.entities.Review;
import com.botanical.gardens.serverside.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser(User user);
}
