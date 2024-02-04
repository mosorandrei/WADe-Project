package com.botanical.gardens.serverside.utils;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.system.stream.StreamManager;

import java.io.InputStream;

public class JenaManager {
    public static ResultSet execQuery(String queryString) {
        OntModel ontoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
        String ontologyFileName = "botanical_garden.owl";
        InputStream in = StreamManager.get().open(ontologyFileName);
        ontoModel.read(in, null);

        Query query = QueryFactory.create(queryString);

        QueryExecution qe = QueryExecutionFactory.create(query, ontoModel);

        return qe.execSelect();
    }
}
