package com.authentication.Authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            (authorize) ->
                authorize
                    .requestMatchers("/user/**", "/getLogging", "/api/books/**")
                    .permitAll()
                    .requestMatchers("/api/books/")
                    .hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/api/books/**")
                    .hasRole("ADMIN")
                    .anyRequest()
                    .authenticated())
        .httpBasic(Customizer.withDefaults())
        .cors(Customizer.withDefaults());
    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //    @Bean
  //    @Override
  //    public UserDetailsService userDetailsService() {
  //      InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
  //      manager.createUser(
  //          User.withUsername("admin")
  //              .password(passwordEncoder().encode("adminpassword"))
  //              .roles("ADMIN")
  //              .build());
  //      manager.createUser(
  //          User.withUsername("user")
  //              .password(passwordEncoder().encode("userpassword"))
  //              .roles("USER")
  //              .build());
  //      return manager;
  //    }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
      throws Exception {
    return configuration.getAuthenticationManager();
  }
}
