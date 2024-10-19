package com.UserAnalytics.BackendAnalytics.Service;

import com.UserAnalytics.BackendAnalytics.Model.Users;
import com.UserAnalytics.BackendAnalytics.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

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
}
