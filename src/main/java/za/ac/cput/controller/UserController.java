package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.*;
import za.ac.cput.service.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<User> create(
            @RequestParam("account") String accountJson,
            @RequestParam("name") String nameJson,
            @RequestParam("contact") String contactJson,
            @RequestParam("address") String addressJson,
            @RequestParam("role") String role,  // New role parameter
            @RequestParam("license") MultipartFile licenseFile,
            @RequestParam("identityDocument") MultipartFile identityDocumentFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Log received data for debugging
            System.out.println("Received account JSON: " + accountJson);
            System.out.println("Received name JSON: " + nameJson);
            System.out.println("Received contact JSON: " + contactJson);
            System.out.println("Received address JSON: " + addressJson);
            System.out.println("Received role: " + role);  // Log role
            System.out.println("Received license file: " + licenseFile.getOriginalFilename());
            System.out.println("Received identity document file: " + identityDocumentFile.getOriginalFilename());

            Account account = objectMapper.readValue(accountJson, Account.class);
            Name name = objectMapper.readValue(nameJson, Name.class);
            Contact contact = objectMapper.readValue(contactJson, Contact.class);
            Address address = objectMapper.readValue(addressJson, Address.class);

            byte[] licenseData = licenseFile.getBytes();
            byte[] identityDocumentData = identityDocumentFile.getBytes();

            // Set the role based on the incoming request
            User.Role userRole = User.Role.valueOf(role.toUpperCase()); // Ensure the role is in uppercase

            User user = new User.Builder()  // Use Builder pattern to create User
                    .setAccount(account)
                    .setName(name)
                    .setContact(contact)
                    .setAddress(address)
                    .setLicense(licenseData)
                    .setIdentityDocument(identityDocumentData)
                    .setRole(userRole)  // Set the role
                    .buildUser();

            // Log user before saving
            System.out.println("Creating user: " + user);

            User createdUser = userService.create(user);
            return ResponseEntity.ok(createdUser);
        } catch (IOException | IllegalArgumentException e) {
            // Log the exception
            System.err.println("Error processing the request: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/read/{userID}")
    public User read(@PathVariable Long userID) {
        return userService.read(userID);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {
        try {
            User authenticatedUser = userService.authenticate(account.getUsername(), account.getPassword());
            if (authenticatedUser != null) {
                System.out.println(authenticatedUser);
                return ResponseEntity.ok("Login successful!");
            } else {
                System.out.println(authenticatedUser);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

    @PutMapping("/update")
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @DeleteMapping("/delete/{userID}")
    public void delete(@PathVariable Long userID) {
        userService.delete(userID);
    }

    @GetMapping("/getAll")
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUser();
    }
}
