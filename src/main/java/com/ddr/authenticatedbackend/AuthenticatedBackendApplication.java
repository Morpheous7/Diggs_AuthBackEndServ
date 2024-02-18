package com.ddr.authenticatedbackend;

import com.ddr.authenticatedbackend.model.Role;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.repository.RoleRepository;
import com.ddr.authenticatedbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuthenticatedBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatedBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder encoder){
        return args -> {
            userRepository.save(new User( "ethan", encoder.encode("passw"), "ROLE_USER"));
            userRepository.save(new User("admin", encoder.encode("passw"), "ROLE_ADMIN, ROLE_USER"));
            userRepository.save(new User("ike", encoder.encode("paw"), "ROLE_USER"));
            roleRepository.save(new Role("ROLE_USER"));
            roleRepository.save(new Role("ROLE_ADMIN"));
           };
    }
}
