package za.ac.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import za.ac.cput.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;

    @Autowired
    @Lazy
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    // Configure authentication with the UserService
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    // Define the security filter chain for HTTP requests
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection for simplicity in development
                .authorizeHttpRequests(authorize -> authorize // Start authorizing requests
                        .requestMatchers("/user/login", "/user/create").permitAll() // Allow login and registration without authentication
                        .requestMatchers("/user/getAll", "/user/count").hasRole("ADMIN") // Only ADMIN can access user data
                        .anyRequest().authenticated()) // All other endpoints require authentication
                .formLogin(form -> form // Configure form-based login
                        .loginProcessingUrl("/user/login") // URL for processing login
                        .defaultSuccessUrl("/home", true) // Redirect to home on successful login
                        .failureUrl("/login?error=true") // Redirect to login page with error on failure
                        .permitAll()) // Allow access to login page
                .logout(logout -> logout // Configure logout
                        .logoutUrl("/logout") // URL for logout
                        .logoutSuccessUrl("/login") // Redirect to login page after logout
                        .permitAll()); // Allow access to logout
        return http.build(); // Build the security filter chain
    }

    // Password encoder bean for securing passwords
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Return an instance of BCryptPasswordEncoder
    }

    // Expose AuthenticationManager as a bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // Retrieve the AuthenticationManager from the configuration
    }
}
