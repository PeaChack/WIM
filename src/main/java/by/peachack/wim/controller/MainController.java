package by.peachack.wim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/wim")
    public String sayHello(){
        return "Hello";
    }
}
