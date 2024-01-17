package com.ismael.gallery.user.service;


import com.ismael.gallery.exceptions.UserNotFoundException;
import com.ismael.gallery.user.model.User;
import com.ismael.gallery.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Other imports...

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create a user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve a user by ID
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update a user
    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            // Update user properties
            User userToUpdate = existingUser.get();
            userToUpdate.setFirstname(updatedUser.getFirstname());
            userToUpdate.setLastname(updatedUser.getLastname());
            userToUpdate.setEmail(updatedUser.getEmail());
            userToUpdate.setPassword(updatedUser.getPassword());
            userToUpdate.setMobile(updatedUser.getMobile());
            userToUpdate.setProfile(updatedUser.getProfile());

            // Update the user in the database
            return userRepository.save(userToUpdate);
        } else {
            // Throw UserNotFoundException when the user with the given ID is not found
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    // Delete a user by ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
