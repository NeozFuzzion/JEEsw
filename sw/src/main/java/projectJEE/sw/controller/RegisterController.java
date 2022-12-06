package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.UserRepository;
import projectJEE.sw.service.UserForm;
import projectJEE.sw.service.UserService;

import java.security.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Controller
public class RegisterController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserForm());

        return ("registration");
    }

    @PostMapping("/register")
    public String registerUser(UserForm userForm){
        if (userRepository.findUserWithName(userForm.getUsername()).isEmpty() && userForm.getPassword().equals(userForm.getConfirmPassword())){
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setUsername(userForm.getUsername());
            user.setCreation_user(Date.from(Instant.now()));
            user.setPassword(passwordEncoder.encode(userForm.getPassword()));
            user.setRole("SUMMONER");
            userRepository.save(user);System.out.println(userForm.getConfirmPassword());
            return ("index");
        }
        System.out.println(userForm.getPassword() == userForm.getConfirmPassword());
        return ("redirect:/register");

    }

    @GetMapping("/login")
    public String login(){
        return ("login");
    }
}
