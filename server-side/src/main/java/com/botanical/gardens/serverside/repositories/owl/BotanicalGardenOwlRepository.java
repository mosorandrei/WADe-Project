package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.JenaManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BotanicalGardenOwlRepository {
    private final AttractionOwlRepository attractionOwlRepository;
    private final TourOwlRepository tourOwlRepository;

    @Autowired
    public BotanicalGardenOwlRepository(AttractionOwlRepository attractionOwlRepository, TourOwlRepository tourOwlRepository) {
        this.attractionOwlRepository = attractionOwlRepository;
        this.tourOwlRepository = tourOwlRepository;
    }

    public List<JSONObject> getAllGardens() {
        String queryString =
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT *
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName ?gardenName .
                        }
                        """;
        ResultSet resultSet = JenaManager.execQuery(queryString);
        List<JSONObject> result = new ArrayList<>();

        while (resultSet.hasNext()) {
            JSONObject garden = new JSONObject();
            QuerySolution solution = resultSet.nextSolution();
            String gardenName = solution.get("gardenName").toString();
            garden.put("gardenName", gardenName);

            garden.put("attractions", attractionOwlRepository.getAttractionsByGarden(gardenName));
            garden.put("tours", tourOwlRepository.getTour(gardenName));
            result.add(garden);
        }
        return result;
    }
}
