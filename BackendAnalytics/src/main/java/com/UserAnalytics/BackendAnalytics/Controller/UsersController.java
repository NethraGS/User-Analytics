package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.Users;
import com.UserAnalytics.BackendAnalytics.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            Optional<Users> foundUser = userService.loginUser(user);

            if (foundUser.isPresent()) {
                Users loggedInUser = foundUser.get();  // Retrieve the found user object
                String role = loggedInUser.getRole().name();
                Long userId = loggedInUser.getId();

                return ResponseEntity.ok().body(Map.of(
                        "message", "Login successful",
                        "role", role,
                        "userId", userId  // Include userId in the response
                ));
            } else {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid username or password"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "An error occurred during login"));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody Users user) {
        System.out.println("Received signup request: " + user);

        Optional<Users> existingUserByEmail = userService.getUserByEmail(user.getEmail());
        if (existingUserByEmail.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }

        Optional<Users> existingUserByUsername = userService.getUserByUsername(user.getUsername());
        if (existingUserByUsername.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already in use"));
        }

        user.setRole(Users.Role.USER);  // Default role to USER during signup
        userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }
}
