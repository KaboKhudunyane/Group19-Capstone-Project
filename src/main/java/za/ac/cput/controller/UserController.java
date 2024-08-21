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
    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping("/read/{userID}")
    public User read(@PathVariable Long userID) {
        return userService.read(userID);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Account account) {
        try {
            User user = userService.authenticate(account.getUsername(), account.getPassword());
            if (user != null) {
                return ResponseEntity.ok("Login successful!");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }@PutMapping("/update")
    public  User update(@RequestBody User user) {
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
}