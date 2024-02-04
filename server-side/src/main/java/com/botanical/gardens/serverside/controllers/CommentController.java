package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.dto.CommentDTO;
import com.botanical.gardens.serverside.services.CommentService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@CrossOrigin(origins = {"http://localhost:5173", "https://botanical-garden-guide.netlify.app"})
@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public void addComment(@RequestBody CommentDTO commentDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        commentService.saveComment(commentDTO);
    }
}
