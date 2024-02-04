package com.botanical.gardens.serverside.services.impl;

import com.botanical.gardens.serverside.entities.Tour;
import com.botanical.gardens.serverside.repositories.jpa.TourRepository;
import com.botanical.gardens.serverside.services.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public void saveTour(Tour tour) {
        tourRepository.save(tour);
    }
}
