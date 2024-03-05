package by.peachack.wim.service;

import by.peachack.wim.model.User;
import by.peachack.wim.model.UserItem;
import by.peachack.wim.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow();
    }
    public void createUser(User user){
        userRepository.save(user);
    }
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
