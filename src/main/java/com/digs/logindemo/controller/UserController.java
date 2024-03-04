package com.digs.logindemo.controller;


import com.digs.logindemo.model.*;
import com.digs.logindemo.service.EventService;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ike Kennedy
 */


@Controller("/user{secUser}")
@CrossOrigin("*")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private SecurityUser secUser;
    private String jwt;
    private User user;
    private final EventService eventService;

    public UserController() {
        eventService = new EventService();
    }

    @GetMapping(value = "/")
    public String userDashboard(@ModelAttribute("secUser") LoginResponseDTO body, @Valid Model model,
                                @Valid BindingResult result){
        this.user = body.getUser();
        this.secUser = body.getSecUser();
        if( this.jwt == null) {
            this.jwt = body.getJwt();
        }
        if(this.jwt == null){
            LoginResponseDTO ldto = (LoginResponseDTO) model.getAttribute("secUser");
            assert ldto != null;
            this.jwt = ldto.getJwt();
        }

        if(this.jwt == null) {
            System.out.println(model.getAttribute("secUser"));
            result.rejectValue("jwt", null,
                    "This account does not exist");
        }

        if(result.hasErrors()){
            model.getAttribute("secUser");
            return "/login/users";
        }
        return "user.html";
    }

    //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/addEvent")
    public ResponseEntity<String> addEvent(@RequestBody Event event) {
        SecurityUser findUser = new SecurityUser(event.getEvent_Organizer(), event);
        eventService.addEvent(event, findUser);
        return ResponseEntity.ok("Hello from secured endpoint");
    }

    //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/eventCreate")
    public ResponseEntity<String> createEvent(@RequestBody Event event) {
        SecurityUser findUser = new SecurityUser(event.getEvent_Organizer(), event);
        eventService.createEvent(event, findUser);
        return ResponseEntity.ok("Hello from secured endpoint");
    }

}
