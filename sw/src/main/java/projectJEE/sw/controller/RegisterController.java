package projectJEE.sw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectJEE.sw.dbEntity.User;
import projectJEE.sw.dbRepository.UserRepository;
import projectJEE.sw.service.UserForm;
import projectJEE.sw.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    public String registerUser(HttpServletRequest request, UserForm userForm, RedirectAttributes redirectAttributes) throws ServletException {

        if (userForm.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*]).{8,}$") && userRepository.findUserWithName(userForm.getUsername()).isEmpty() && userForm.getPassword().equals(userForm.getConfirmPassword())){
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setUsername(userForm.getUsername());
            user.setCreation_user(Date.from(Instant.now()));
            user.setPassword(passwordEncoder.encode(userForm.getPassword()));
            user.setRole("SUMMONER");
            userRepository.save(user);
            request.login(userForm.getUsername(), userForm.getPassword());
            return ("/index");
        }
        if (!userForm.getPassword().matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*]).{8,}$") || !userForm.getPassword().equals(userForm.getConfirmPassword()))
            redirectAttributes.addFlashAttribute("messagep","Your password don't follow the requirement");
        if (!userRepository.findUserWithName(userForm.getUsername()).isEmpty())
            redirectAttributes.addFlashAttribute("messageu","This username is already taken");
        return ("redirect:/register");

    }

    @GetMapping("/login")
    public String login(){
        return ("login");
    }
}
