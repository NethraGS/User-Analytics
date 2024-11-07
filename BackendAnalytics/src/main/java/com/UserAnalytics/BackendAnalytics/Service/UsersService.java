package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.Users;
import com.UserAnalytics.BackendAnalytics.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    private ConcurrentHashMap<String, Long> activeSessions = new ConcurrentHashMap<>(); // Stores sessionId -> userId

    public void registerUser(Users user) {
        System.out.println("Registering user: " + user);
        usersRepository.save(user); // No password hashing
    }

    public Optional<Users> loginUser(Users user) {
        Optional<Users> foundUser = usersRepository.findByUsername(user.getUsername());
        if (foundUser.isPresent() && user.getPassword().equals(foundUser.get().getPassword())) {
            return foundUser;
        }
        return Optional.empty();
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    // Optionally store session IDs on the server
    public void storeSession(String sessionId, Long userId) {
        activeSessions.put(sessionId, userId);
    }

    // Optionally remove a session when the user logs out
    public void removeSession(String sessionId) {
        activeSessions.remove(sessionId);
    }
}
