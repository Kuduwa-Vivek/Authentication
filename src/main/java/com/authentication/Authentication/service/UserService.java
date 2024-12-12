package com.authentication.Authentication.service;

import com.authentication.Authentication.entity.User;
import com.authentication.Authentication.record.LoginRequest;
import com.authentication.Authentication.repo.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  public String save(User user) {
    User user1 = new User();
    user1.setPassword(passwordEncoder.encode(user.getPassword()));
    user1.setUsername(user.getUsername());
    user1.setRoles(user.getRoles());
    Optional<User> byUsername = userRepository.findByUsername(user1.getUsername());
    if (byUsername.isPresent()) {
      return "username already exists";
    }
    userRepository.save(user1);
    return "registered successfully";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = new User();
    User user1 = userRepository.findByUsername(username).get();
    user.setUsername(user1.getUsername());
    user.setPassword(user1.getPassword());
    user.setRoles(user1.getRoles());
    return (UserDetails) user;
  }

  public String loginUser(LoginRequest loginRequest) {

    String username = loginRequest.username();
    String password = loginRequest.password();
    Optional<User> byUsername = userRepository.findByUsername(username);
    if (byUsername.isPresent()) {
      if (passwordEncoder.matches(password, byUsername.get().getPassword())) {
        return "Logged In successfully";
      }
      return "Invalid Password";
    }
    return "User Not Found";
  }
}
