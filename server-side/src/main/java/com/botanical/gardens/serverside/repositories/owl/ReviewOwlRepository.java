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
import java.util.UUID;

@Component
public class ReviewOwlRepository {
    private final OntologyManager ontologyManager = new OntologyManager();
    private final UserRepository userRepository;

    @Autowired
    public ReviewOwlRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addReviewIndividual(int reviewScore, String tourName, String firstName, String lastName) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = ontologyManager.getOntologyFromFile(oh);

        String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";
        OWLIndividual newReview = oh.createIndividual(ontologyName + "#Review-" + UUID.randomUUID());
        OWLClass reviewClass = ontologyManager.getClass("Review");
        OWLIndividual tour = ontologyManager.getIndividualByProperty(o, "hasTourName", tourName).orElseThrow(() -> new RuntimeException("Tour not found!"));
        Long id = userRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new RuntimeException("User not found!")).getId();
        OWLIndividual user = ontologyManager.getIndividualByProperty(o, "hasUserId", id.intValue()).orElseThrow(() -> new RuntimeException("User not found!"));

        OWLObjectProperty isReviewOf = ontologyManager.getObjectProperty("isReviewOf");
        OWLObjectProperty hasReview = ontologyManager.getObjectProperty("hasReview");
        OWLObjectProperty isGivenBy = ontologyManager.getObjectProperty("isGivenBy");
        OWLObjectProperty hasGiven = ontologyManager.getObjectProperty("hasGiven");

        oh.applyChange(
                oh.associateIndividualWithClass(o, reviewClass, newReview),
                oh.addDataToIndividual(o, newReview, ontologyManager.getDataProperty("hasReviewContent"), reviewScore),
                oh.addObjectProperty(o, tour, hasReview, newReview),
                oh.addObjectProperty(o, newReview, isReviewOf, tour),
                oh.addObjectProperty(o, newReview, isGivenBy, user),
                oh.addObjectProperty(o, user, hasGiven, newReview)
        );
        ontologyManager.saveOntologyToFile(oh, o);
    }

    public List<JSONObject> getReview(String tourName, String gardenName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?content ?fullName
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?tour bot:isTourOf ?garden .
                            ?tour bot:hasTourName "%s" .
                            
                            OPTIONAL {
                             ?tour bot:hasReview ?review .
                             ?review bot:hasReviewContent ?content .
                             ?review bot:isGivenBy ?user .
                             ?user bot:hasFirstName ?fn.
                             ?user bot:hasLastName ?ln.
                             BIND(concat(?fn, " ", ?ln) as ?fullName)
                            }
                        }
                        """, gardenName, tourName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject review = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            if (solution.get("content") != null) {
                review.put("reviewContent", solution.get("content").toString());
                review.put("reviewAuthor", solution.get("fullName").toString());
                result.add(review);
            }
        }
        return result;
    }
}
