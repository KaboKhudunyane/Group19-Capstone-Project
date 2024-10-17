package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IService<User, Long>, UserDetailsService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Setter for PasswordEncoder (delayed injection to break the cycle)
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public User create(User user) {
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder is not configured");
        }

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        if (user.getRole() == null) {
            user.setRole(User.Role.USER);
        }

        return userRepository.save(user);
    }
    @Override
    public User read(Long userID) {
        Optional<User> userOptional = userRepository.findById(userID);
        return userOptional.orElse(null);
    }
    public User authenticate(String username, String password) {
        // Ensure passwordEncoder is available
        if (passwordEncoder == null) {
            throw new IllegalStateException("PasswordEncoder is not configured");
        }
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    // Update an existing user
    @Override
    public User update(User user) {
        // Save the updated user to the repository and return it
        return userRepository.save(user);
    }

    // Delete a user by ID
    @Override
    public void delete(Long userID) {
        // Delete the user by ID
        userRepository.deleteById(userID);
    }

    // Get all users
    @Override
    public List<User> getAll() {
        // Retrieve and return a list of all users
        return userRepository.findAll();
    }

    // Count the number of users
    public long countUser() {
        // Return the count of users from the repository
        return userRepository.count();
    }

    // Load user by username for Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) { // If user is found
            User user = optionalUser.get();
            // Build and return UserDetails object with username, password, and roles
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername()) // Get username
                    .password(user.getPassword()) // Get password (ensure it's encoded)
                    .roles(user.getRole().name()) // Assign user roles
                    .build();
        } else {
            // Throw an exception if user not found
            throw new UsernameNotFoundException("User not found");
        }
    }
}
