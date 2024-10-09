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
@CrossOrigin(origins = "http://localhost:5173")
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
            @RequestParam("license") MultipartFile licenseFile,
            @RequestParam("identityDocument") MultipartFile identityDocumentFile) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Account account = objectMapper.readValue(accountJson, Account.class);
            Name name = objectMapper.readValue(nameJson, Name.class);
            Contact contact = objectMapper.readValue(contactJson, Contact.class);
            Address address = objectMapper.readValue(addressJson, Address.class);

            byte[] licenseData = licenseFile.getBytes();
            byte[] identityDocumentData = identityDocumentFile.getBytes();

            User user = new User();
            user.setAccount(account);
            user.setName(name);
            user.setContact(contact);
            user.setAddress(address);
            user.setLicense(licenseData);
            user.setIdentityDocument(identityDocumentData);

            User createdUser = userService.create(user);
            return ResponseEntity.ok(createdUser);
        } catch (IOException e) {
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