package com.fieldbooking.server.controller;

import com.fieldbooking.server.model.entity.User;
import com.fieldbooking.server.service.implementation.UserServiceImpl;
import com.fieldbooking.server.util.ErrorMessage;
import com.fieldbooking.server.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    private final UserServiceImpl userService;
    private final UserValidator userValidator;

    @Autowired
    public UserRestController(UserServiceImpl userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/list")
    public List<User> list() {
        return userService.findAll();
    }

    @GetMapping("/byId")
    public Optional<User> byId(Long id) {
        return userService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody @Valid User user, BindingResult result) {

        if (user.getId() != null) {
            if (!userService.findById(user.getId()).get().getUsername().equals(user.getUsername())) {

                userValidator.validateUser(user, result);

                if (result.hasErrors()) {
                    ErrorMessage errorMessage = new ErrorMessage(result);
                    return errorMessage.getResponseEntity();
                }

                User save = userService.save(user);
                return ResponseEntity.ok(save);
            } else {
                User save = userService.save(user);
                return ResponseEntity.ok(save);
            }

        } else {
            userValidator.validateUser(user, result);

            if (result.hasErrors()) {
                ErrorMessage errorMessage = new ErrorMessage(result);
                return errorMessage.getResponseEntity();
            }

            User save = userService.save(user);
            return ResponseEntity.ok(save);
        }

    }

    @DeleteMapping("/delete")
    public void delete(Long id) {
        userService.delete(id);
    }

    @GetMapping("/login")
    public User login(String username, String password) {
        return userService.login(username, password);
    }
}
