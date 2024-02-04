package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.JenaManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttractionOwlRepository {
    public List<String> getAttractionsByTour(String gardenName, String tourName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?name
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?tour bot:isTourOf ?garden .
                            ?tour bot:hasTourName "%s" .
                            
                            ?tour bot:hasAttraction ?attraction .
                            ?attraction bot:hasAttractionName ?name
                        }
                        """, gardenName, tourName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<String> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            QuerySolution solution = resultSet.nextSolution();

            result.add(solution.get("name").toString());
        }
        return result;
    }

    public List<JSONObject> getAttractionsByGarden(String gardenName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?name ?type ?description
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?attraction bot:isAttractionOf ?garden .
                            ?attraction bot:hasAttractionName ?name .
                            ?attraction bot:hasAttractionType ?type .
                            ?attraction bot:hasAttractionDescription ?description
                        }
                        """, gardenName);
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject attraction = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            attraction.put("attractionName", solution.get("name").toString());
            attraction.put("attractionType", solution.get("type").toString());
            attraction.put("attractionDescription", solution.get("description").toString());

            result.add(attraction);
        }
        return result;
    }
}
