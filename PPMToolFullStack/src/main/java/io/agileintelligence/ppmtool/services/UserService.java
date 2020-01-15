package io.agileintelligence.ppmtool.services;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.exceptions.ProjectIdException;
import io.agileintelligence.ppmtool.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveOrUpdateUser(User user) {
        try {
            return (User) userRepository.save(user);
        } catch(Exception e) {
            throw new ProjectIdException("User ID '" + user.getId() + "' already exists!");
        }
    }

    public User findUserByUsername(String username) {
        User user = userRepository.findByUsername(username.toUpperCase());

        if(user == null) {
            throw new ProjectIdException("User with username '" + username + "' doesn't exist");
        }
        return user;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username.toUpperCase());
        if(user == null) {
            throw new ProjectIdException("User '" + username + "' doesn't exist");
        }
        userRepository.delete(user);
    }

}
