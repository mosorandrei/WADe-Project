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
public class CommentOwlRepository {
    private final OntologyManager ontologyManager = new OntologyManager();
    private final UserRepository userRepository;

    @Autowired
    public CommentOwlRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addCommentIndividual(String commentContent, String tourName, String firstName, String lastName) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = ontologyManager.getOntologyFromFile(oh);

        String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";
        OWLIndividual newComment = oh.createIndividual(ontologyName + "#Comment-" + UUID.randomUUID());
        OWLClass commentClass = ontologyManager.getClass("Comment");
        OWLIndividual tour = ontologyManager.getIndividualByProperty(o, "hasTourName", tourName).orElseThrow(() -> new RuntimeException("Tour not found!"));
        Long id = userRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(() -> new RuntimeException("User not found!")).getId();
        OWLIndividual user = ontologyManager.getIndividualByProperty(o, "hasUserId", id.intValue()).orElseThrow(() -> new RuntimeException("User not found!"));

        OWLObjectProperty isCommentOf = ontologyManager.getObjectProperty("isCommentOf");
        OWLObjectProperty hasComment = ontologyManager.getObjectProperty("hasComment");
        OWLObjectProperty isGivenBy = ontologyManager.getObjectProperty("isGivenBy");
        OWLObjectProperty hasGiven = ontologyManager.getObjectProperty("hasGiven");

        oh.applyChange(
                oh.associateIndividualWithClass(o, commentClass, newComment),
                oh.addDataToIndividual(o, newComment, ontologyManager.getDataProperty("hasCommentContent"), commentContent),
                oh.addObjectProperty(o, tour, hasComment, newComment),
                oh.addObjectProperty(o, newComment, isCommentOf, tour),
                oh.addObjectProperty(o, newComment, isGivenBy, user),
                oh.addObjectProperty(o, user, hasGiven, newComment)
        );
        ontologyManager.saveOntologyToFile(oh, o);
    }

    public List<JSONObject> getComment(String tourName, String gardenName) {
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
                             ?tour bot:hasComment ?comment .
                             ?comment bot:hasCommentContent ?content .
                             ?comment bot:isGivenBy ?user .
                             ?user bot:hasFirstName ?fn.
                             ?user bot:hasLastName ?ln.
                             BIND(concat(?fn, " ", ?ln) as ?fullName)
                            }
                        }
                        """, gardenName, tourName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject comment = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            if (solution.get("content") != null) {
                comment.put("commentContent", solution.get("content").toString());
                comment.put("commentAuthor", solution.get("fullName").toString());

                result.add(comment);
            }
        }
        return result;
    }
}
