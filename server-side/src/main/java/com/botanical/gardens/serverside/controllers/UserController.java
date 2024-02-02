package com.botanical.gardens.serverside.controllers;

import com.botanical.gardens.serverside.dto.FeInputErrorDTO;
import com.botanical.gardens.serverside.dto.LoginRequestDTO;
import com.botanical.gardens.serverside.dto.RegisterRequestDTO;
import com.botanical.gardens.serverside.dto.UserDTO;
import com.botanical.gardens.serverside.entities.User;
import com.botanical.gardens.serverside.services.UserService;
import com.botanical.gardens.serverside.utils.PasswordEncryptionTool;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        String email = loginRequestDTO.getEmail();
        String password = loginRequestDTO.getPassword();
        if(email.isEmpty() || password.isEmpty()) {
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
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        String firstName = registerRequestDTO.getFirstName();
        String lastName = registerRequestDTO.getLastName();
        String email = registerRequestDTO.getEmail();
        String password = registerRequestDTO.getPassword();
        String confirmedPassword = registerRequestDTO.getConfirmedPassword();
        boolean hasAcceptedTerms = registerRequestDTO.isAcceptedTerms();

        if(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmedPassword.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(FeInputErrorDTO.builder()
                    .errorMessage("Please fill every empty input!").build());
        } else if(!password.equals(confirmedPassword)) {
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
}
