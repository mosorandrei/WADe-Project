package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.dto.FeInputErrorDTO;
import com.botanical.gardens.serverside.dto.LoginRequestDTO;
import com.botanical.gardens.serverside.dto.RegisterRequestDTO;
import com.botanical.gardens.serverside.dto.UserDTO;
import com.botanical.gardens.serverside.entities.Attraction;
import com.botanical.gardens.serverside.entities.BotanicalGarden;
import com.botanical.gardens.serverside.entities.Tour;
import com.botanical.gardens.serverside.entities.User;
import com.botanical.gardens.serverside.services.*;
import com.botanical.gardens.serverside.utils.OntologyManager;
import com.botanical.gardens.serverside.utils.PasswordEncryptionTool;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@CrossOrigin(origins = {"http://localhost:5173", "https://65bfad59f775d96f899d7dd1--lustrous-selkie-ce070a.netlify.app"})
@RequestMapping("/user")
@RestController
public class UserController {
    private final BotanicalGardenService botanicalGardenService;

    private final AttractionService attractionService;

    private final CommentService commentService;

    private final ReviewService reviewService;

    private final TourService tourService;

    private final UserService userService;

    @Autowired
    public UserController(BotanicalGardenService botanicalGardenService, AttractionService attractionService, CommentService commentService, ReviewService reviewService, TourService tourService, UserService userService) {
        this.botanicalGardenService = botanicalGardenService;
        this.attractionService = attractionService;
        this.commentService = commentService;
        this.reviewService = reviewService;
        this.tourService = tourService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() throws OWLOntologyStorageException, OWLOntologyCreationException {
        OntologyManager oh = new OntologyManager();
        try {
            new OntologyManager().getOntologyFromFile(oh);
        } catch (Exception ex) {
            new OntologyManager().buildInitialRdf();
            initiateDatabase();
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        if (email.isEmpty() || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("Please fill every empty input").build());
        } else {
            try {
                User user = userService.getUserByEmail(email);
                if (PasswordEncryptionTool.checkPassword(password, user.getPassword())) {

                    return ResponseEntity.ok(UserDTO.builder()
                            .lastName(user.getLastName())
                            .email(email)
                            .build());
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                            .errorMessage("Invalid credentials!").build());
                }

            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                        .errorMessage("User not found in our system!").build());
            }
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) throws OWLOntologyCreationException, OWLOntologyStorageException {
        String firstName = registerRequestDTO.getFirstName();
        String lastName = registerRequestDTO.getLastName();
        String email = registerRequestDTO.getEmail();
        String password = registerRequestDTO.getPassword();
        String confirmedPassword = registerRequestDTO.getConfirmedPassword();
        boolean hasAcceptedTerms = registerRequestDTO.isAcceptedTerms();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("Please fill every empty input!").build());
        } else if (!password.equals(confirmedPassword)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("No matching passwords").build());
        } else if (!hasAcceptedTerms) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("Please accept terms and conditions!").build());
        }

        try {
            userService.getUserByEmail(email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("Email already used!").build());
        } catch (RuntimeException e) {
            userService.saveUser(User.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(PasswordEncryptionTool.hashPassword(password))
                    .build());

            return ResponseEntity.ok(UserDTO.builder()
                    .lastName(lastName)
                    .email(email)
                    .build());
        }
    }

    private void initiateDatabase() {
        Attraction orchid = Attraction.builder()
                .name("Orchid")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction sunflower = Attraction.builder()
                .name("Sunflower")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction tulip = Attraction.builder()
                .name("Tulip")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction azalea = Attraction.builder()
                .name("Azalea")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction chrysanthemum = Attraction.builder()
                .name("Chrysanthemum")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction lotus = Attraction.builder()
                .name("Lotus")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction iris = Attraction.builder()
                .name("Iris")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction daisy = Attraction.builder()
                .name("Daisy")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction hibiscus = Attraction.builder()
                .name("Hibiscus")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction lavender = Attraction.builder()
                .name("Lavender")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction cosmos = Attraction.builder()
                .name("Cosmos")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        Attraction lantana = Attraction.builder()
                .name("Lantana")
                .type("Landscaping")
                .description("Plantae kingdom")
                .build();

        attractionService.saveAttraction(orchid);
        attractionService.saveAttraction(sunflower);
        attractionService.saveAttraction(tulip);
        attractionService.saveAttraction(azalea);
        attractionService.saveAttraction(chrysanthemum);
        attractionService.saveAttraction(lotus);
        attractionService.saveAttraction(iris);
        attractionService.saveAttraction(daisy);
        attractionService.saveAttraction(hibiscus);
        attractionService.saveAttraction(lavender);
        attractionService.saveAttraction(cosmos);
        attractionService.saveAttraction(lantana);

        LocalDate date = LocalDate.of(2024, 2, 5);
        Tour iasiTour1 = Tour.builder()
                .name("Discover the beauty of Iasi")
                .description("Enjoy a tour of our beautiful flowers")
                .guideName("Adrian Smau")
                .attractions(List.of(orchid, sunflower, chrysanthemum))
                .startHour(LocalDateTime.of(date, LocalTime.of(11, 0)))
                .endHour(LocalDateTime.of(date, LocalTime.of(13, 0)))
                .totalSeats(10)
                .build();

        Tour iasiTour2 = Tour.builder()
                .name("Open gates night")
                .description("Free entry for all")
                .guideName("Andrei Mosor")
                .attractions(List.of(orchid, daisy))
                .startHour(LocalDateTime.of(date, LocalTime.of(21, 0)))
                .endHour(LocalDateTime.of(date, LocalTime.of(22, 0)))
                .totalSeats(25)
                .build();

        Tour newYorkTour = Tour.builder()
                .name("Grandest tour of 2024")
                .description("Jewel of New York")
                .guideName("Ion Popa")
                .attractions(List.of(tulip, azalea, lotus, chrysanthemum, orchid, lantana))
                .startHour(LocalDateTime.of(date, LocalTime.of(15, 0)))
                .endHour(LocalDateTime.of(date, LocalTime.of(16, 0)))
                .totalSeats(110)
                .build();

        tourService.saveTour(iasiTour1);
        tourService.saveTour(iasiTour2);
        tourService.saveTour(newYorkTour);

        BotanicalGarden iasiGarden = BotanicalGarden.builder()
                .name("Iasi Garden")
                .attractions(List.of(orchid, sunflower, chrysanthemum, daisy))
                .tours(List.of(iasiTour1, iasiTour2))
                .build();

        BotanicalGarden newYorkGarden = BotanicalGarden.builder()
                .name("New York Garden")
                .attractions(List.of(tulip, azalea, lotus, chrysanthemum, orchid, lantana))
                .tours(List.of(newYorkTour))
                .build();

        botanicalGardenService.saveBotanicalGarden(iasiGarden);
        botanicalGardenService.saveBotanicalGarden(newYorkGarden);
    }
}
