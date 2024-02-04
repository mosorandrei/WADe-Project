package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.OntologyManager;
import org.semanticweb.owlapi.model.*;
import org.springframework.stereotype.Service;

@Service
public class UserOwlRepository {
    private final OntologyManager ontologyManager = new OntologyManager();
    private final String ontologyName = "http://smaumosorteam.com/ontologies/2024/botanical_garden.owl";

    public void addUserIndividual(String firstName, String lastName, int id) throws OWLOntologyCreationException, OWLOntologyStorageException {
        OntologyManager oh = new OntologyManager();
        OWLOntology o = ontologyManager.getOntologyFromFile(oh);

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
}
