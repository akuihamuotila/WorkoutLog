package fi.haagahelia.workoutlog.web;

import fi.haagahelia.workoutlog.domain.Exercise;
import fi.haagahelia.workoutlog.domain.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ExerciseController {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @GetMapping("/exercises")
    // Method to show the list of exercises
    public String listExercises(Model model) {
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "exerciselist";
    }

    @GetMapping("/addexercise")
    // Method to show the form for adding a new exercise
    public String showAddExerciseForm(Model model) {
        model.addAttribute("exercise", new Exercise());
        return "addexercise";
    }

    @PostMapping("/addexercise")
    // Method to save a new exercise
    public String saveExercise(@ModelAttribute Exercise exercise) {
        exerciseRepository.save(exercise);
        return "redirect:/exercises";
    }
}