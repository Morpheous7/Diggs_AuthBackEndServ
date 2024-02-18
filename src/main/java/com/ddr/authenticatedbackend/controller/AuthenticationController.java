package com.ddr.authenticatedbackend.controller;


import com.ddr.authenticatedbackend.model.*;
import com.ddr.authenticatedbackend.service.AuthenticationService;
import com.ddr.authenticatedbackend.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ike Kennedy
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final EventService eventService;

    public AuthenticationController() {
        this.authenticationService = new AuthenticationService();
        this.eventService = new EventService();
    }

    public AuthenticationController(AuthenticationService authenticationService, EventService eventService) {
        this.authenticationService = authenticationService;
        this.eventService = eventService;
    }

   /// @PreAuthorize("hasRole('USER')")
    @PostMapping("/register")
    public User registerUser(@RequestBody RegistrationDTO body){
        return  authenticationService.registerUser(body.getUsername(), body.getPassword());
    }

  //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body) throws InstantiationException, IllegalAccessException {
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }

  //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/login/user/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        SecurityUser findUser = new SecurityUser(event.getEvent_Organizer(), event);
        eventService.addEvent(event, findUser);
        return ResponseEntity.ok("Hello from secured endpoint");
    }

  //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/login/user/eventCreate")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        SecurityUser findUser = new SecurityUser(event.getEvent_Organizer(), event);
        eventService.createEvent(event, findUser);
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    @GetMapping("/user")
    public String helloUserController(){
        return " Hello User";
    }

  //  @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/")
    public String helloAdminController(){
        return " Admin level Access User";
    }

  //  @PreAuthorize(value = "hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

   // @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/login/user")
    public ResponseEntity<String> sayHelloToUser() {
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
