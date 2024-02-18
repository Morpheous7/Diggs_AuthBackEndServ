package com.ddr.authenticatedbackend.controller;


import com.ddr.authenticatedbackend.model.LoginResponseDTO;
import com.ddr.authenticatedbackend.model.RegistrationDTO;
import com.ddr.authenticatedbackend.model.User;
import com.ddr.authenticatedbackend.service.AuthenticationService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Ike Kennedy
 */

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class UserController {

    private final AuthenticationService authenticationService;

    public UserController() {
        authenticationService = new AuthenticationService();
    }

    //@PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestParam String username, @RequestParam String password) {
        return authenticationService.loginUser(username, password);
    }

    //@PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/login.html")
    public LoginResponseDTO loginAUser(@RequestParam String username, @RequestParam String password) {
        return authenticationService.loginUser(username, password);
    }

    //@PreAuthorize("hasRole('USER')")
    @PostMapping("/register")
    public Optional<User> registerUser(@RequestBody RegistrationDTO body) {
        return Optional.ofNullable(authenticationService.registerUser(body.getUsername(), body.getPassword()));
    }

    //@PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public Optional<User> registerAUser(@RequestBody RegistrationDTO body) {
        return Optional.ofNullable(authenticationService.registerUser(body.getUsername(), body.getPassword()));
    }


}
