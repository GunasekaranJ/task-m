package Backendprojects.Project1.Service;

import Backendprojects.Project1.Repository.UserRepository;
import Backendprojects.Project1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

}
