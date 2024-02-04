package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.JenaManager;
import com.botanical.gardens.serverside.utils.OntologyManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.semanticweb.owlapi.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserOwlRepository {
    private final OntologyManager ontologyManager = new OntologyManager();

    public void addUserIndividual(String firstName, String lastName, int id) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = ontologyManager.getOntologyFromFile(oh);

        String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";
        OWLIndividual newUser = oh.createIndividual(ontologyName + "#" + firstName + "-" + lastName + "-" + id);
        OWLClass userClass = ontologyManager.getClass("User");
        oh.applyChange(
                oh.associateIndividualWithClass(o, userClass, newUser),
                oh.addDataToIndividual(o, newUser, ontologyManager.getDataProperty("hasFirstName"), firstName),
                oh.addDataToIndividual(o, newUser, ontologyManager.getDataProperty("hasLastName"), lastName),
                oh.addDataToIndividual(o, newUser, ontologyManager.getDataProperty("hasUserId"), id)
        );
        ontologyManager.saveOntologyToFile(oh, o);
    }

    public List<JSONObject> getParticipants(String tourName, String gardenName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?fn ?ln
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?tour bot:isTourOf ?garden .
                            ?tour bot:hasTourName "%s" .
                            
                            OPTIONAL {
                             ?tour bot:hasParticipant ?participant .
                             ?participant bot:hasFirstName ?fn .
                             ?participant bot:hasLastName ?ln .
                            }
                        }
                        """, gardenName, tourName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject participant = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            if (solution.get("fn") != null) {
                participant.put("firstName", solution.get("fn").toString());
                participant.put("lastName", solution.get("ln").toString());
                result.add(participant);
            }
        }
        return result;
    }
}
