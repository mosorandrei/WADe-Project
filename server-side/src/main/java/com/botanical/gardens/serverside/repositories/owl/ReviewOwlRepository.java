package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.JenaManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewOwlRepository {
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
                             ?review bot:isReviewOf ?user .
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
            if (solution.get("reviewContent") != null) {
                review.put("reviewContent", solution.get("content").toString());
                review.put("reviewAuthor", solution.get("fullName").toString());
                result.add(review);
            }
        }
        return result;
    }
}
