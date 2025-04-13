package fi.haagahelia.workoutlog.web;

import fi.haagahelia.workoutlog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkoutSetController {

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;

    @Autowired
    private WorkoutSetRepository workoutSetRepository;

    @GetMapping("/workoutExercise/{id}/addset")
    // Method to show the form for adding a set to a workout exercise
    public String showAddSetForm(@PathVariable("id") Long workoutExerciseId, Model model) {
        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid WorkoutExercise ID: " + workoutExerciseId));
        model.addAttribute("workoutExercise", workoutExercise);
        model.addAttribute("workoutSet", new WorkoutSet());
        return "addset";
    }

    @PostMapping("/workoutExercise/{id}/addset")
    // Method to add a set to a workout exercise
    public String addSet(@PathVariable("id") Long workoutExerciseId,
                         @RequestParam("reps") int reps,
                         @RequestParam("weight") double weight) {

        WorkoutExercise workoutExercise = workoutExerciseRepository.findById(workoutExerciseId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid WorkoutExercise ID: " + workoutExerciseId));

        WorkoutSet set = new WorkoutSet();
        set.setWorkoutExercise(workoutExercise);
        set.setReps(reps);
        set.setWeight(weight);

        workoutSetRepository.save(set);

        return "redirect:/";
    }

    @PostMapping("/workoutset/delete/{id}")
    // Method to delete a workout set
    public String deleteSet(@PathVariable("id") Long id) {
        WorkoutSet set = workoutSetRepository.findById(id).orElse(null);
        if (set != null) {
            workoutSetRepository.delete(set);
        }
        return "redirect:/";
    }
}