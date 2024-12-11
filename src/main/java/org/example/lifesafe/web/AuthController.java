package org.example.lifesafe.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.lifesafe.model.entity.User;
import org.example.lifesafe.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final IUserService userService;

    @Autowired
    public AuthController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        if (userService.registerUser(user)) {
            return "redirect:/auth/login";
        }
        model.addAttribute("error", "Email already exists.");
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request, Model model) {
        if (userService.loginUser(email, password)) {
            User user = userService.getUserByEmail(email);
            request.getSession().setAttribute("loggedInUser", user);
            return "redirect:/";
        }
        model.addAttribute("error", "Invalid email or password.");
        return "login";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/auth/login";
    }

}
