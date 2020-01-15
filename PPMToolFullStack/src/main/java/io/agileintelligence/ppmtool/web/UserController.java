package io.agileintelligence.ppmtool.web;

import io.agileintelligence.ppmtool.domain.User;
import io.agileintelligence.ppmtool.services.MapValidationErrorService;
import io.agileintelligence.ppmtool.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationErrorService(bindingResult);
        if(errorMap != null)
            return errorMap;
        User user1 = userService.saveOrUpdateUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUsername(@PathVariable String username) {
        User user = userService.findUserByUsername(username.toUpperCase());
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUserByUsername(@PathVariable String username) {
        userService.deleteUserByUsername(username);
        return new ResponseEntity<String>("User '" + username + "' was deleted successfully", HttpStatus.OK);
    }
}
