package com.UserAnalytics.BackendAnalytics.Controller;

import com.UserAnalytics.BackendAnalytics.Model.Users;
import com.UserAnalytics.BackendAnalytics.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user, HttpServletResponse response) {
        try {
            Optional<Users> foundUser = userService.loginUser(user);

            if (foundUser.isPresent()) {
                Users loggedInUser = foundUser.get();  // Retrieve the found user object
                String role = loggedInUser.getRole().name();
                Long userId = loggedInUser.getId();

                // Generate a session ID and set it in a cookie
                String sessionId = UUID.randomUUID().toString();
                Cookie sessionCookie = new Cookie("sessionId", sessionId);
                sessionCookie.setHttpOnly(true);  // Prevent client-side JavaScript access
                sessionCookie.setSecure(true);    // Send only over HTTPS
                sessionCookie.setPath("/");      // Cookie accessible across the entire domain
                sessionCookie.setMaxAge(3600);   // Cookie expiry time (1 hour)

                // Add session cookie to the response
                response.addCookie(sessionCookie);

                // Optionally, store the session ID on the server (e.g., in a database or cache)
                // userService.storeSession(sessionId, loggedInUser);

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

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletResponse response) {
        try {
            // Invalidate the session by removing the session cookie
            Cookie sessionCookie = new Cookie("sessionId", null);
            sessionCookie.setMaxAge(0); // Expire immediately
            sessionCookie.setPath("/");  // Path to which the cookie applies
            response.addCookie(sessionCookie); // Send expired cookie to the client

            return ResponseEntity.ok(Map.of("message", "Logout successful"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "An error occurred during logout"));
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
