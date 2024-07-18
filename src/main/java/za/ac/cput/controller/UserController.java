package za.ac.cput.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.User;
import za.ac.cput.service.UserService;
import java.util.List;
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/create")
    public User create(@RequestBody User user){
        return userService.create(user);
    }
    @GetMapping("/read/{UserID}")
    public User read(@PathVariable Long UserID){
        return userService.read(UserID);
    }
    @PutMapping("/update")
    public User update(@RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("/delete/{UserID}")
    public void delete (@PathVariable Long UserID){
        userService.delete(UserID);
    }
    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAll();
    }
}