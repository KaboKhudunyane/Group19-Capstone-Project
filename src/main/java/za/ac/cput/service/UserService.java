package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Account;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service // Indicates that this class is a service component in the Spring context
public class UserService implements IService<User, Long>, UserDetailsService {

    private final UserRepository userRepository; // Repository to handle user data
    private final PasswordEncoder passwordEncoder; // Password encoder for encrypting passwords

    // Constructor for dependency injection of UserRepository and PasswordEncoder
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new user
    @Override
    public User create(User user) {
        // Encrypt the password before saving the user
        String encryptedPassword = passwordEncoder.encode(user.getAccount().getPassword());

        // Create a new Account object with the encrypted password
        Account newAccount = new Account.Builder()
                .setUsername(user.getAccount().getUsername()) // Set the username from the user object
                .setPassword(encryptedPassword) // Set the encrypted password
                .buildAccount(); // Build the new Account instance

        // Set the new Account object to the user
        user.setAccount(newAccount); // Assuming you have a setter for account in User
        // Save the user to the repository and return the saved user
        return userRepository.save(user);
    }

    // Read a user by ID
    @Override
    public User read(Long userID) {
        // Find the user by ID and return it; if not found, return null
        Optional<User> userOptional = userRepository.findById(userID);
        return userOptional.orElse(null);
    }

    // Authenticate a user with username and password
    public User authenticate(String username, String password) {
        // Find user by username
        Optional<User> optionalUser = userRepository.findByAccountUsername(username);
        if (optionalUser.isPresent()) { // If the user is found
            User user = optionalUser.get();
            // Verify the password using the password encoder
            if (passwordEncoder.matches(password, user.getAccount().getPassword())) {
                return user; // Return the user if password matches
            }
        }
        return null; // Return null if authentication fails
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
        return userRepository.countUser();
    }

    // Load user by username for Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Find the user by username
        Optional<User> optionalUser = userRepository.findByAccountUsername(username);
        if (optionalUser.isPresent()) { // If user is found
            User user = optionalUser.get();
            // Build and return UserDetails object with username, password, and roles
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getAccount().getUsername()) // Get username
                    .password(user.getAccount().getPassword()) // Get password (ensure it's encoded)
                    .roles(user.getRole().name()) // Assign user roles
                    .build();
        } else {
            // Throw an exception if user not found
            throw new UsernameNotFoundException("User not found");
        }
    }
}
