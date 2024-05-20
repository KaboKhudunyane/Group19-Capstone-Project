package za.ac.cput.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Contact;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;
import java.util.List;
@Service
public class UserService implements IService<User, String>{
    private UserRepository userRepository;
    @Autowired
    UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
    @Override
    public User read(String userID) {
        return userRepository.findUser(userID);
    }
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }
    @Override
    void delete(String userID) {
        userRepository.deleteUser(userID);
    }
    @Override
    public List<User> getAllContact( ){
        return userRepository.getAllUsers();
    }
}
