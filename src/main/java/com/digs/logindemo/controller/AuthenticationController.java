package com.digs.logindemo.controller;


import com.digs.logindemo.model.*;
import com.digs.logindemo.service.AuthenticationService;
import com.digs.logindemo.service.EventService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

/**
 * @author Ike Kennedy
 */


@Controller("/")
public class AuthenticationController {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;
    private final BCryptPasswordEncoder passwordEncoder;
    private LoginResponseDTO loginResponseDTO = null;
    private static SecurityUser securityUser = null;
    public AuthenticationController() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.authenticationService = new AuthenticationService();
    }

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    /// @PreAuthorize("hasRole('USER')")
    @PostMapping("/register/save")
        public String registration(@ModelAttribute("user") User userDto,
                @Valid BindingResult result, @Valid Model model){
        //userDto.setUser((User) model.getAttribute("user"));
        String encodedPassword = "BCrypt" + this.passwordEncoder.encode(userDto.getPassword());

        SecurityUser secUser = new SecurityUser(userDto);
        secUser.setPassword(encodedPassword);
        if(authenticationService.isUserPresent(secUser.getUser())) {
            result.rejectValue("email", "99999",
                    "There is already an account registered with the same email");
        }
        if(result.hasErrors()){
                model.addAttribute("user", userDto);
                return "/register";
        }
        authenticationService.registerUser(secUser.getUser());
        log.debug("Just registered :" + secUser.getUser());
        return "redirect:/register?success";
    }

    /// @PreAuthorize("hasRole('USER')")
    @GetMapping("/register")
    public String registerAUser(@ModelAttribute User user, @Valid Model model) {
        System.out.println(user.toString());
        model.addAttribute("user", user);
        return "register";
    }

    //  @PreAuthorize(value = "hasRole('USER')")
    @PostMapping("/login/users")
    public String login(@ModelAttribute("userForm") RegistrationDTO body, @Valid Model model,
                            @Valid BindingResult result) {
        String encoder = "BCrypt" + this.passwordEncoder.encode(body.getPassword());

        if(!authenticationService.isUserPresent(body.getUsername(), encoder)) {
            result.rejectValue("username", null,
                    "This account does not exist");
        }
        if(result.hasErrors()){
            model.addAttribute("userForm", body);
            return "/login";
        }
        this.loginResponseDTO = authenticationService.loginUser(body.getUsername(),encoder);
        model.addAttribute("secUser", this.loginResponseDTO);
        //securityUser = (SecurityUser) authenticationService.loadUserByUsername(body.getUsername());
        System.out.println(this.loginResponseDTO.getJwt());
        log.debug("Just authenticated :" + this.loginResponseDTO.getUser()+ " with a JWT token of: "
        + this.loginResponseDTO.getJwt());

        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute RegistrationDTO usr, @Valid Model model) {
        System.out.println(usr.toString());
        model.addAttribute("userForm", usr);
        System.out.println(model.getAttribute("userForm"));
        return "login";
    }



    @RequestMapping("/login?success")
    public String loginUser(@ModelAttribute("secUser") LoginResponseDTO lRdto, @Valid Model model,
                            @Valid BindingResult result) {
        System.out.println(lRdto.toString());
        System.out.println(model.getAttribute("secUser"));
        model.addAttribute("secUser", lRdto);
        System.out.println(model.getAttribute("secUser"));
        return "user";
    }



}
