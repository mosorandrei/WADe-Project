package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.dto.ReviewDTO;
import com.botanical.gardens.serverside.repositories.owl.ReviewOwlRepository;
import com.botanical.gardens.serverside.services.ReviewService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewOwlRepository reviewOwlRepository;

    @Autowired
    public ReviewServiceImpl(ReviewOwlRepository reviewRepository) {
        this.reviewOwlRepository = reviewRepository;
    }

    @Override
    public void saveReview(ReviewDTO reviewDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        reviewOwlRepository.addReviewIndividual(reviewDTO.getScore(), reviewDTO.getTourName(), reviewDTO.getFirstName(), reviewDTO.getLastName());
    }
}
