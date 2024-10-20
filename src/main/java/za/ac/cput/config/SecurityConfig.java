package za.ac.cput.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import za.ac.cput.service.UserDetailsServiceImp;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())  // Enable CORS
                .csrf(csrf -> csrf.disable())      // Disable CSRF for stateless APIs
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/user/create", "api/admin/create", "/api/carInformation/getall", "/user/getAll", "**/count").permitAll() // Public routes
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")  // Admin-only routes
                        .requestMatchers("/user/**").hasAuthority("USER")    // User-only routes
                        .anyRequest().authenticated()  // Secure other routes
                )
                .httpBasic(Customizer.withDefaults())  // Basic Authentication
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .userDetailsService(userDetailsService)  // Custom UserDetailsService
                .build();
    }

    @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder(); // Use BCryptPasswordEncoder
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }
    }
