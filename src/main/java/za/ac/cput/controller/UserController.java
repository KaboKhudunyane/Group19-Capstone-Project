package za.ac.cput.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @GetMapping("/count")
    public long getUserCount() {
        return userService.countUser();
    }
}