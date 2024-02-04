package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.dto.ParticipateDTO;
import com.botanical.gardens.serverside.services.TourService;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/tours")
@CrossOrigin(origins = {"http://localhost:5173", "https://botanical-garden-guide.netlify.app"})
@RestController
public class ToursController {
    private final TourService tourService;

    @Autowired
    public ToursController(TourService tourService) {
        this.tourService = tourService;
    }

    @PostMapping("/participate")
    public void participate(@RequestBody ParticipateDTO participateDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        tourService.participate(participateDTO);
    }
}
