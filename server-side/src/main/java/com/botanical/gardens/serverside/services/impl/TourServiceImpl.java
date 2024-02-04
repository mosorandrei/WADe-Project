package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.dto.ParticipateDTO;
import com.botanical.gardens.serverside.entities.Tour;
import com.botanical.gardens.serverside.repositories.jpa.TourRepository;
import com.botanical.gardens.serverside.repositories.owl.TourOwlRepository;
import com.botanical.gardens.serverside.services.TourService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourOwlRepository tourOwlRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository, TourOwlRepository tourOwlRepository) {
        this.tourRepository = tourRepository;
        this.tourOwlRepository = tourOwlRepository;
    }

    @Override
    public void saveTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void participate(ParticipateDTO tourDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        tourOwlRepository.participate(tourDTO.getTourName(), tourDTO.getFirstName(), tourDTO.getLastName());
    }
}
