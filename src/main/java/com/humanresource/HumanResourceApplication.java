package com.humanresource;

import com.humanresource.auth.User;
import com.humanresource.auth.UserRepo;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableRabbit
public class HumanResourceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HumanResourceApplication.class, args);
    }

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public HumanResourceApplication(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = User.builder()
                .id(1)
                .username("admin")
                .password(passwordEncoder.encode("Test@123"))
                .role("ADMIN")
                .build();
        userRepo.save(user);
    }
}
