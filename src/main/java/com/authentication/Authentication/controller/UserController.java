package com.authentication.Authentication.controller;

import com.authentication.Authentication.entity.User;
import com.authentication.Authentication.record.LoginRequest;
import com.authentication.Authentication.service.UserService;
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

  @PostMapping("/reg")
  public ResponseEntity<String> register(@RequestBody User user) {
    return ResponseEntity.ok(userService.save(user));
  }

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest input) {
    String user = userService.loginUser(input);
    if (user.equals("Logged In successfully")) {
      return ResponseEntity.status(HttpStatus.OK).body(user);
    } else if (user.equals("Invalid Password")) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
  }
}
