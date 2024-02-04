package com.botanical.gardens.serverside.services;

import com.botanical.gardens.serverside.dto.ParticipateDTO;
import com.botanical.gardens.serverside.entities.*;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public interface TourService {
    void saveTour(Tour tour);
    void participate(ParticipateDTO tourDTO) throws OWLOntologyCreationException, OWLOntologyStorageException;
}
