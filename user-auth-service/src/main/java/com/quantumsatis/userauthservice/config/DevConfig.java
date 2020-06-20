package com.quantumsatis.userauthservice.config;

import com.quantumsatis.userauthservice.repository.UserRepository;
import com.quantumsatis.userauthservice.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("dev")
@Configuration
@RequiredArgsConstructor
public class DevConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner demoData(UserRepository repository) {
        return (args) -> {
            User user = createUser("admin", "password", "admin@mail.ru")
                    .setFirstName("Admin")
                    .setLastName("Admin")
                    .setMiddleName("Admin");

            repository.save(user);
            repository.save(createUser("user", "password", "user@mail.ru"));
            repository.save(createUser("manager", "password", "manager@mail.ru"));
            repository.save(createUser("boss", "password", "boss@mail.ru"));
        };
    }

    private User createUser(String username, String password, String email) {
        return new User()
                .setUsername(username)
                .setPassword(passwordEncoder.encode(password))
                .setEmail(email);
    }
}
