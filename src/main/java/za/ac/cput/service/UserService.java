package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import java.util.List;
@Service
public class UserService implements IService<User, String> {
    private final UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // Method to create a new user
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    // Method to read a user by their userID
    @Override
    public User read(String userID) {
        return userRepository.findByUserID(userID);
    }
    // Method to update an existing user
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
    // Method to delete a user by their userID
    public void delete(String userID) {
        userRepository.deleteByUserID(userID);
    }
    // Method to get all users
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}