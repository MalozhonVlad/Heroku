package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class HerokuApplication {

    private final PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(HerokuApplication.class, args);
    }

    @Bean
    public CommandLineRunner preload(MessageRepository messageRepository, UserRepository userRepository) {
        return args -> {
            Set<Role> roleSet = new HashSet<>();
            roleSet.add(Role.USER);
            roleSet.add(Role.ADMIN);
            User user = new User();
            user.setUsername("1");
            user.setPassword(passwordEncoder.encode("1"));
            user.setPassword2(passwordEncoder.encode("1"));
            user.setEmail("some@some.com");
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setCredentialsNonExpired(true);
            user.setAccountNonLocked(true);
            userRepository.save(user);
        };
    }
}
