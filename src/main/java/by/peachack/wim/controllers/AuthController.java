package by.peachack.wim.controllers;

import by.peachack.wim.dto.UserDTO;
import by.peachack.wim.exceptions.UsernameAlreadyExistsException;
import by.peachack.wim.services.user.UserService;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final MessageSource messageSource;
    public AuthController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO, Locale locale) throws UsernameAlreadyExistsException {
        if (userService.usernameExists(userDTO.getLogin()))
            throw new UsernameAlreadyExistsException(messageSource.getMessage("auth.errors.user.alreadyExists", new String[]{userDTO.getLogin()}, locale));

        userService.registerNewUser(userDTO);
        return ResponseEntity.ok(messageSource.getMessage("auth.status.user.register.ok",null, locale));
    }

    @PostMapping("/login")
    public Object login() {
        return "null";
    }

    @ExceptionHandler
    private void handleException(Exception e){
        System.out.println("some exception");
        e.printStackTrace();
        //TODO implement
    }

}
