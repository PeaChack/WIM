package by.peachack.wim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/register")
    public Object register() {
        return null;
    }

    @PostMapping
    public Object login() {
        return null;
    }
}
