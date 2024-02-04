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
public class TourOwlRepository {
    private final AttractionOwlRepository attractionOwlRepository;
    private final ReviewOwlRepository reviewOwlRepository;
    private final CommentOwlRepository commentOwlRepository;
    private final UserOwlRepository userOwlRepository;

    @Autowired
    public TourOwlRepository(AttractionOwlRepository attractionOwlRepository, ReviewOwlRepository reviewOwlRepository, CommentOwlRepository commentOwlRepository, UserOwlRepository userOwlRepository) {
        this.attractionOwlRepository = attractionOwlRepository;
        this.reviewOwlRepository = reviewOwlRepository;
        this.commentOwlRepository = commentOwlRepository;
        this.userOwlRepository = userOwlRepository;
    }

    public List<JSONObject> getTour(String gardenName) {
        String queryString = String.format(
                """
                        PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                        PREFIX owl: <http://www.w3.org/2002/07/owl#>
                        PREFIX bot: <http://smaumosorteam.com/ontologies/2024/botanical_garden.owl#>

                        SELECT ?tourName ?tourDescription ?tourStartHour ?tourEndHour ?tourGuideName
                        WHERE {
                            ?garden rdf:type bot:Garden .
                            ?garden bot:hasGardenName "%s" .
                            ?tour bot:isTourOf ?garden .
                            ?tour bot:hasTourName ?tourName .
                            ?tour bot:hasTourDescription ?tourDescription .
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
            tour.put("tourName",tourName);
            tour.put("tourDescription",solution.get("tourDescription").toString());
            tour.put("tourStartHour",solution.get("tourStartHour").toString());
            tour.put("tourEndHour",solution.get("tourEndHour").toString());
            tour.put("tourGuideName",solution.get("tourGuideName").toString());
            tour.put("tourAttractionNames",attractionOwlRepository.getAttractionsByTour(gardenName, tourName));
            tour.put("tourComments",commentOwlRepository.getComment(tourName, gardenName));
            tour.put("tourReviews",reviewOwlRepository.getReview(tourName, gardenName));
            tour.put("tourParticipants",userOwlRepository.getParticipants(tourName, gardenName));

            result.add(tour);
        }
        return result;
    }
}
