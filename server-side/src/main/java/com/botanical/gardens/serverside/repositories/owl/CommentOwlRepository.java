package com.botanical.gardens.serverside.repositories.owl;

import com.botanical.gardens.serverside.utils.JenaManager;
import net.minidev.json.JSONObject;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentOwlRepository {
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
                             ?comment bot:isCommentOf ?user .
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
