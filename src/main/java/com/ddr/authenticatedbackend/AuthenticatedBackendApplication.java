package com.ddr.authenticatedbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthenticatedBackendApplication {
    public static void main(String[] args) {


        SpringApplication.run(AuthenticatedBackendApplication.class, args);
    }




   /* @Bean
    CommandLineRunner run(){
        return args -> {
            this.userRepository.save(new User( "ethan", this.encoder.encode("passw"), "ROLE_USER"));
            this.userRepository.save(new User("admin", this.encoder.encode("passw"), "ROLE_ADMIN, ROLE_USER"));
            this.userRepository.saveAndFlush(new User("ike", this.encoder.encode("paw"), "ROLE_USER"));
            this.userRepository.saveAndFlush(new User("kent", this.encoder.encode("pas"), "ROLE_USER"));
            this.userRepository.flush();
           };
    }*/
}
