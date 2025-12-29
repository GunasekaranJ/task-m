package Backendprojects.Project1.Service;

import Backendprojects.Project1.Repository.UserRepository;
import Backendprojects.Project1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository UserRepository;

    public User createUser(User user) {
        return UserRepository.save(user);
    }

    public User getUserById(Long id) {
        return UserRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

}
