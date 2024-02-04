package com.botanical.gardens.serverside.repositories.jpa;

import com.botanical.gardens.serverside.entities.Comment;
import com.botanical.gardens.serverside.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByUser(User user);
}
