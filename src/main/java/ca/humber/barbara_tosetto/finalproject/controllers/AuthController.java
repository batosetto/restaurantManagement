package ca.humber.barbara_tosetto.finalproject.controllers;

import ca.humber.barbara_tosetto.finalproject.models.MyUser;
import ca.humber.barbara_tosetto.finalproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController implements org.springframework.boot.web.servlet.error.ErrorController {

    @Value("${restaurant.name}")
    private String name;
    private UserService userService;

    public AuthController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/error")
    public String handleError(){
        return "auth/error";
    }
    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String message){
        model.addAttribute("message", message);
        model.addAttribute("restaurantName", name);
        return "auth/login";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req,
                         HttpServletResponse res,
                         Authentication auth){
        new SecurityContextLogoutHandler().logout(req, res, auth);
        return "redirect:/login?message=You have been logged out";
    }
    @GetMapping("/register")
    public String register(Model model, @RequestParam(required = false) String message){
        model.addAttribute("user", new MyUser());
        model.addAttribute("message", message);
        return "auth/register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute MyUser user){
        int saveUserCode = userService.createUser(user);
        if(saveUserCode == 0){
            return "redirect:/register?message=User already exists";
        };
        return "redirect:/login?message=Registration successful";
    }
}
