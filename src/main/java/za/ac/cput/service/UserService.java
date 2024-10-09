package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IService<User, Long> {
    private final UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    @Override
    public User read(Long userID) {
        return userRepository.findByUserID(userID);
    }
    public User authenticate(String username, String password) {
        System.out.println("Attempting to authenticate user: " + username);
        Optional<User> optionalUser = userRepository.findByAccountUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Log the retrieved password (only for debugging; remove in production)
            System.out.println("Retrieved password for user: " + user.getAccount().getPassword());
            if (user.getAccount().getPassword().equals(password)) {
                System.out.println("User authenticated successfully: " + username);
                return user;
            } else {
                System.out.println("Invalid password for user: " + username);
            }
        } else {
            System.out.println("User not found: " + username);
        }
        return null;
    }
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
    @Override
    public void delete(Long userID) {
        userRepository.deleteByUserID(userID);
    }
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
    public long countUser (){
        return userRepository.countUser();
    }
}
