package by.peachack.wim.services.user;

import by.peachack.wim.dto.UserDTO;
import by.peachack.wim.model.inventory.Inventory;
import by.peachack.wim.model.inventory.InventoryItem;
import by.peachack.wim.model.user.Authority;
import by.peachack.wim.model.user.User;
import by.peachack.wim.repositories.InventoryRepository;
import by.peachack.wim.repositories.UserRepository;
import by.peachack.wim.services.inventory.InventoryService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//TODO refactor using UserMapper Interface
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthorityService authorityService;
    private final InventoryRepository inventoryRepository;
    private final MessageSource messageSource;

    private final PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       AuthorityService authorityService, InventoryRepository inventoryRepository,
                       MessageSource messageSource,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authorityService = authorityService;
        this.inventoryRepository = inventoryRepository;
        this.messageSource = messageSource;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow();
    }

    public User registerNewUser(UserDTO userDTO) {
        User userEntity = convertToEntity(userDTO);
        User user = userRepository.save(userEntity);
        Inventory inventory = new Inventory(user,0,0);
        inventoryRepository.save(inventory);
        return user;
    }

    public boolean usernameExists(String login) {
        return userRepository.existsByUsername(login);
    }

    public void deleteUser(UserDTO userDTO) {
        userRepository.delete(convertToEntity(userDTO));
    }

    private User convertToEntity(UserDTO userDTO) {

        String username = userDTO.getLogin();
        String password = encoder.encode(userDTO.getPassword());
        if (usernameExists(username)) {
            return userRepository
                    .findByUsernameAndPassword(username, password)
                    .orElseThrow();
        } else {
            Authority defaultAuthority = authorityService.getDefaultAuthority();
            return new User(UUID.randomUUID(), username, password, List.of(defaultAuthority));
        }
    }

    private UserDTO convertToDTO(User user) {
        String username = user.getUsername();
        String password = encoder.encode(user.getPassword());
        return new UserDTO(username, password);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByUsername(username).orElseThrow();
        Hibernate.initialize(userDetails.getAuthorities());
        return userDetails;
    }
}
