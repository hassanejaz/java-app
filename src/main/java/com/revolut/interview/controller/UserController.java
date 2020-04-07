package com.revolut.interview.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.revolut.interview.accessor.User;
import com.revolut.interview.converter.UserConverter;
import com.revolut.interview.dto.Date;
import com.revolut.interview.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@Validated
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    public UserController(UserService userService, UserConverter userConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
    }

    @PutMapping("/hello/{userName}")
    public ResponseEntity<Object> createUser(@JsonFormat(pattern = "yyyy-MM-dd") @RequestBody Date dateOfBirth,
                                             @PathVariable @Pattern(message = "The username must contain only letters", regexp = "^[a-zA-Z]+$") String userName) {

        final User user = userConverter.convert(dateOfBirth, userName);

        return userService.updateUser(user);
    }

    @GetMapping("/hello/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username) {
        return userService.getDaysUntilBirthday(username);
    }
}
