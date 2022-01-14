package com.example.webback.controlller;

import com.example.webback.config.JwtProvider;
import com.example.webback.config.Token;
import com.example.webback.converter.UserConverter;
import com.example.webback.dto.UserDto;
import com.example.webback.entity.User;
import com.example.webback.exception.FailedRegisterUserException;
import com.example.webback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserConverter userConverter;


    @PostMapping(value = "/register",
            produces = "application/json")
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {
        User user = userConverter.fromUserDtoToUser(userDto);
        try {
            userService.saveUser(user);
            String tokenStr = jwtProvider.generateToken(user.getUsername());
            Token token = new Token(tokenStr);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } catch (FailedRegisterUserException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserDto userDto) {
        User user = userConverter.fromUserDtoToUser(userDto);
        if (userService.checkUser(user)) {
            String tokenStr = jwtProvider.generateToken(user.getUsername());
            Token token = new Token(tokenStr);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }
}
