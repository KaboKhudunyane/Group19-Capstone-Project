package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.*;
import za.ac.cput.service.UserService;
import za.ac.cput.config.JwtUtil; // Adjusted import statement

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil; // Use your existing JwtUtil class for token handling

    // Create User (Registration)
    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<User> create(
            @RequestParam(value = "role", required = false) String role,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("name") String nameJson,
            @RequestParam("contact") String contactJson,
            @RequestParam("address") String addressJson,
            @RequestParam("license") MultipartFile licenseFile,
            @RequestParam("identityDocument") MultipartFile identityDocumentFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            User.Role userRole = (role != null) ? User.Role.valueOf(role.toUpperCase()) : User.Role.USER; // Default to USER if not provided
            Name name = objectMapper.readValue(nameJson, Name.class);
            Contact contact = objectMapper.readValue(contactJson, Contact.class);
            Address address = objectMapper.readValue(addressJson, Address.class);
            byte[] licenseData = licenseFile.getBytes();
            byte[] identityDocumentData = identityDocumentFile.getBytes();

            User user = new User.Builder()
                    .setUsername(username)
                    .setPassword(password)  // Ensure password is encrypted in UserService
                    .setRole(userRole)
                    .setName(name)
                    .setContact(contact)
                    .setAddress(address)
                    .setLicense(licenseData)
                    .setIdentityDocument(identityDocumentData)
                    .buildUser();

            System.out.println("Creating user: " + user);

            User createdUser = userService.create(user);
            return ResponseEntity.ok(createdUser);
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error processing the request: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Read User by ID
    @GetMapping("/read/{userID}")
    public ResponseEntity<User> read(@PathVariable Long userID) {
        User user = userService.read(userID);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Login (handled by Spring Security, no longer needed in this form)
    // If you want to keep a login endpoint to return the JWT token, use this instead:
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            // Use userService to authenticate and fetch the user details
            User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
            if (authenticatedUser != null) {
                // Create a UserDetails object from the authenticated user
                UserDetails userDetails = org.springframework.security.core.userdetails.User
                        .withUsername(authenticatedUser.getUsername())
                        .password(authenticatedUser.getPassword())
                        .roles(authenticatedUser.getRole().name()) // Assuming Role is an enum
                        .build();

                // Generate JWT Token
                String token = jwtUtil.generateToken(userDetails);
                System.out.println("Login successful for user: " + authenticatedUser);
                return ResponseEntity.ok(token); // Return the JWT token
            } else {
                System.out.println("Login failed for username: " + user.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }


    // Update User
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Delete User
    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<Void> delete(@PathVariable Long userID) {
        userService.delete(userID);
        return ResponseEntity.noContent().build();
    }

    // Get All Users (ROLE_ADMIN only)
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }

    // Get User Count (ROLE_ADMIN only)
    @GetMapping("/count")
    public ResponseEntity<Long> getUserCount() {
        long userCount = userService.countUser();
        return ResponseEntity.ok(userCount);
    }
}
