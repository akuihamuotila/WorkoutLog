package fi.haagahelia.workoutlog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    // Method to show the login page
    public String login() {
        return "login";
    }
}