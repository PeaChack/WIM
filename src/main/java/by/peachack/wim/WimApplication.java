package by.peachack.wim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WimApplication {

    public static void main(String[] args) {
        SpringApplication.run(WimApplication.class, args);
    }

}
