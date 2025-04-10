package fi.haagahelia.workoutlog.web;

import fi.haagahelia.workoutlog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("workouts", workoutRepository.findAll()); // myöhemmin: filter by user
        model.addAttribute("username", currentUser.getUsername());
        return "index";
    }
}