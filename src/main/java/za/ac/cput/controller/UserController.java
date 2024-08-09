package za.ac.cput.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import za.ac.cput.domain.*;
import za.ac.cput.service.UserService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleAllExceptions(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestPart("user") String userJson,
                                       @RequestPart(value = "license", required = false) MultipartFile licenseFile,
                                       @RequestPart(value = "identityDocument", required = false) MultipartFile identityDocumentFile)
            throws IOException {
        // Convert the JSON string to a User object
        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);

        byte[] licenseBytes = licenseFile != null ? licenseFile.getBytes() : null;
        byte[] identityDocumentBytes = identityDocumentFile != null ? identityDocumentFile.getBytes() : null;

        User newUser = new User.Builder()
                .setName(user.getName())
                .setContact(user.getContact())
                .setAddress(user.getAddress())
                .setLicense(licenseBytes)
                .setIdentityDocument(identityDocumentBytes)
                .buildUser();

        User createdUser = userService.create(newUser);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/read/{userID}")
    public ResponseEntity<User> read(@PathVariable Long userID) {
        User user = userService.read(userID);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        User updatedUser = userService.update(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<Void> delete(@PathVariable Long userID) {
        userService.delete(userID);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        List<User> users = userService.getAll();
        return ResponseEntity.ok(users);
    }
}
