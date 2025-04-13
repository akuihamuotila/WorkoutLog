package fi.haagahelia.workoutlog.web;

import fi.haagahelia.workoutlog.domain.*;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userRepository.findByUsername(userDetails.getUsername());
        model.addAttribute("workouts", workoutRepository.findAll());
        model.addAttribute("username", currentUser.getUsername());
        return "index";
    }

    @GetMapping("/addworkout")
    public String showAddWorkoutForm() {
        return "addworkout";
    }

    @PostMapping("/addworkout")
    public String addWorkout(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername());
        Workout workout = new Workout();
        workout.setDate(date);
        workout.setUser(user);
        workoutRepository.save(workout);
        return "redirect:/";
    }

    @PostMapping("/workout/delete/{id}")
    public String deleteWorkout(@PathVariable("id") Long id) {
        workoutRepository.deleteById(id);
        return "redirect:/";
    }
} 