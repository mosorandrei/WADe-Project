package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.repositories.jpa.UserRepository;
import com.botanical.gardens.serverside.utils.JenaManager;
import com.botanical.gardens.serverside.utils.OntologyManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.semanticweb.owlapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class TourOwlRepository {
    private final OntologyManager ontologyManager = new OntologyManager();
    private final AttractionOwlRepository attractionOwlRepository;
    private final ReviewOwlRepository reviewOwlRepository;
    private final CommentOwlRepository commentOwlRepository;
    private final UserOwlRepository userOwlRepository;
    private final UserRepository userRepository;

    @Autowired
    public TourOwlRepository(AttractionOwlRepository attractionOwlRepository, ReviewOwlRepository reviewOwlRepository, CommentOwlRepository commentOwlRepository, UserOwlRepository userOwlRepository, UserRepository userRepository) {
        this.attractionOwlRepository = attractionOwlRepository;
        this.reviewOwlRepository = reviewOwlRepository;
        this.commentOwlRepository = commentOwlRepository;
        this.userOwlRepository = userOwlRepository;
        this.userRepository = userRepository;
    }

    public void participate(String tourName, String firstName, String lastName) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = ontologyManager.getOntologyFromFile(oh);

        OWLIndividual tour = ontologyManager.getIndividualByProperty(o, "hasTourName", tourName).orElseThrow(() -> new RuntimeException("Tour not found!"));
        Long id = userRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new RuntimeException("User not found!")).getId();
        OWLIndividual user = ontologyManager.getIndividualByProperty(o, "hasUserId", id.intValue()).orElseThrow(() -> new RuntimeException("User not found!"));

        OWLObjectProperty isParticipantOf = ontologyManager.getObjectProperty("isParticipantOf");
        OWLObjectProperty hasParticipant = ontologyManager.getObjectProperty("hasParticipant");

        OWLDataProperty hasTourTotalSeats = ontologyManager.getDataProperty("hasTourTotalSeats");

        Set<OWLDataPropertyAssertionAxiom> existingAxioms = o.getDataPropertyAssertionAxioms(tour);
        for (OWLDataPropertyAssertionAxiom oldAssertion : existingAxioms) {
            if (oldAssertion.getProperty().equals(hasTourTotalSeats)) {
                oh.applyChange(new RemoveAxiom(o, oldAssertion),
                        oh.addDataToIndividual(o, tour, hasTourTotalSeats, Integer.parseInt(oldAssertion.getObject().getLiteral()) - 1));
            }
        }

        oh.applyChange(
                oh.addObjectProperty(o, tour, hasParticipant, user),
                oh.addObjectProperty(o, user, isParticipantOf, tour)
        );
        ontologyManager.saveOntologyToFile(oh, o);
    }

    public List<JSONObject> getTour(String gardenName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?tourName ?tourDescription ?tourStartHour ?tourEndHour ?tourGuideName ?tourTotalSeats
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?tour bot:isTourOf ?garden .
                            ?tour bot:hasTourName ?tourName .
                            ?tour bot:hasTourDescription ?tourDescription .
                            ?tour bot:hasTourTotalSeats ?tourTotalSeats .
                            ?tour bot:hasTourStartHour ?tourStartHour .
                            ?tour bot:hasTourEndHour ?tourEndHour .
                            ?tour bot:hasTourGuideName ?tourGuideName .
                        }
                        """, gardenName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject tour = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            String tourName = solution.get("tourName").toString();
            tour.put("tourName", tourName);
            tour.put("tourDescription", solution.get("tourDescription").toString());
            tour.put("tourStartHour", solution.get("tourStartHour").toString());
            tour.put("tourEndHour", solution.get("tourEndHour").toString());
            tour.put("tourTotalSeats", solution.get("tourTotalSeats").toString());
            tour.put("tourGuideName", solution.get("tourGuideName").toString());
            tour.put("tourAttractionNames", attractionOwlRepository.getAttractionsByTour(gardenName, tourName));
            tour.put("tourComments", commentOwlRepository.getComment(tourName, gardenName));
            tour.put("tourReviews", reviewOwlRepository.getReview(tourName, gardenName));
            tour.put("tourParticipants", userOwlRepository.getParticipants(tourName, gardenName));

            result.add(tour);
        }
        return result;
    }
}
