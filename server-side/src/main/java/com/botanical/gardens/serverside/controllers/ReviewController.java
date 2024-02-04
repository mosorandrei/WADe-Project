package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.dto.ReviewDTO;
import com.botanical.gardens.serverside.services.ReviewService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/reviews")
@CrossOrigin(origins = {"http://localhost:5173", "https://botanical-garden-guide.netlify.app"})
@RestController
public class ReviewController {
    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/add")
    public void addReview(@RequestBody ReviewDTO reviewDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        reviewService.saveReview(reviewDTO);
    }
}
