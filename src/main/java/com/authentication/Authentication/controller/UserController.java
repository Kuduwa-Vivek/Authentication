package com.authentication.Authentication.controller;

import com.authentication.Authentication.entity.User;
import com.authentication.Authentication.record.LoginRequest;
import com.authentication.Authentication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired private UserService userService;

  @Autowired private AuthenticationManager authenticationManager;

  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @PostMapping("/reg")
  public ResponseEntity<String> register(@RequestBody User user) {
    try {
      logger.info("User registered successfully: {}", user.getUsername());
      return ResponseEntity.ok(userService.save(user));
    } catch (Exception e) {
      logger.error("Error occurred during registration for user: {}", user.getUsername());
      return ResponseEntity.status(500).body("Registration failed");
    }
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest input) {
    logger.info("Login attempt for user: {}", input.username());
    String user = userService.loginUser(input);
    if (user.equals("Logged In successfully")) {
      logger.info("Login successful for user: {}", input.username());
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else if (user.equals("Invalid Password")) {
      logger.warn("Login failed for user: {}. Reason: Invalid Password", input.username());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    } else {
      logger.warn("Login failed for user: {}. Reason: User not found", input.username());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    }
  }
}
