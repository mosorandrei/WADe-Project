package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.entities.*;
import com.botanical.gardens.serverside.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RequestMapping("/botanical-gardens")
@RestController
public class BotanicalGardenController {
    private final BotanicalGardenService botanicalGardenService;

    private final AttractionService attractionService;

    private final CommentService commentService;

    private final ReviewService reviewService;

    private final TourService tourService;

    private final UserService userService;

    @Autowired
    public BotanicalGardenController(BotanicalGardenService botanicalGardenService, AttractionService attractionService, CommentService commentService, ReviewService reviewService, TourService tourService, UserService userService) {
        this.botanicalGardenService = botanicalGardenService;
        this.attractionService = attractionService;
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BotanicalGarden>> getBotanicalGardens() {
        List<BotanicalGarden> gardens = botanicalGardenService.fetchBotanicalGardens();

        if (!gardens.isEmpty()) {
            return ResponseEntity.ok(gardens);
        } else {
            // build one garden if no content TODO
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getBotanicalGardenNames() {
        List<String> gardenNames = botanicalGardenService.fetchBotanicalGardens().stream().map(BotanicalGarden::getName).toList();
        if (!gardenNames.isEmpty()) {
            return ResponseEntity.ok(gardenNames);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getBotanicalGardenByName(@PathVariable String name) {
        try {
            BotanicalGarden garden = botanicalGardenService.findBotanicalGardenByName(name);
            return ResponseEntity.ok(garden);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    @PostMapping("/createForTesting")
    public ResponseEntity<String> createGarden() {
        User andrei = User.builder()
                .firstName("Andrei")
                .lastName("Mosor")
                .email("mosorandrei49@gmail.com")
                .password("salut")
                .build();

        User adi = User.builder()
                .firstName("Adrian")
                .lastName("Smau")
                .email("adriansmau@gmail.com")
                .password("salut")
                .build();

        userService.saveUser(andrei);
        userService.saveUser(adi);
        Comment comment1 = Comment.builder()
                .content("comment1")
                .user(andrei)
                .build();

        Comment comment2 = Comment.builder()
                .content("comment2")
                .user(adi)
                .build();

        Review review1 = Review.builder()
                .rating(10)
                .user(andrei)
                .build();

        Review review2 = Review.builder()
                .rating(8)
                .user(adi)
                .build();

        commentService.saveComment(comment1);
        commentService.saveComment(comment2);
        reviewService.saveReview(review1);
        reviewService.saveReview(review2);

        Attraction attraction1 = Attraction.builder()
                .name("attraction1")
                .type("flower")
                .description("this is a flower")
                .photo(null)
                .comments(List.of(comment1))
                .reviews(List.of(review1))
                .build();

        Attraction attraction2 = Attraction.builder()
                .name("attraction2")
                .type("tree")
                .description("this is a tree")
                .photo(null)
                .comments(List.of(comment2))
                .reviews(List.of(review2))
                .build();

        Attraction attraction3 = Attraction.builder()
                .name("attraction3")
                .type("arbust")
                .description("this is an arbust")
                .photo(null)
                .build();

        attractionService.saveAttraction(attraction1);
        attractionService.saveAttraction(attraction2);
        attractionService.saveAttraction(attraction3);

        LocalDate date = LocalDate.of(2024, 2, 5);
        Date sqlStartDate = Date.valueOf(date.atTime(10, 0).toLocalDate());
        Date sqlEndDate = Date.valueOf(date.atTime(12, 0).toLocalDate());
        Tour tour = Tour.builder()
                .name("Magic Tour")
                .description("this is magic tour")
                .guideName("Vasile")
                .attractions(List.of(attraction1, attraction2))
                .startHour(sqlStartDate)
                .endHour(sqlEndDate)
                .totalSeats(7)
                .participants(List.of(andrei, adi))
                .build();

        tourService.saveTour(tour);

        BotanicalGarden garden = BotanicalGarden.builder()
                .name("Best Garden")
                .attractions(List.of(attraction1, attraction2, attraction3))
                .tours(List.of(tour))
                .build();

        botanicalGardenService.saveBotanicalGarden(garden);
        return ResponseEntity.ok("Initialize is done");
    }


    public BotanicalGardenService getBotanicalGardenService() {
        return botanicalGardenService;
    }

    public AttractionService getAttractionService() {
        return attractionService;
    }

    public CommentService getCommentService() {
        return commentService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public TourService getTourService() {
        return tourService;
    }

    public UserService getUserService() {
        return userService;
    }
}
