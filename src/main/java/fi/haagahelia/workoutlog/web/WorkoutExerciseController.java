package fi.haagahelia.workoutlog.web;

import fi.haagahelia.workoutlog.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WorkoutExerciseController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private WorkoutExerciseRepository workoutExerciseRepository;
    
    @GetMapping("/workout/{id}/addexercise")
    // Method to show the form for adding an exercise to a workout
    public String showAddExerciseForm(@PathVariable("id") Long workoutId, Model model) {
        Workout workout = workoutRepository.findById(workoutId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid workout Id:" + workoutId));
        model.addAttribute("workout", workout);
        model.addAttribute("exercises", exerciseRepository.findAll());
        return "addworkoutexercise";
    }

    @PostMapping("/workout/{id}/addexercise")
    // Method to add an exercise to a workout
    public String addExerciseToWorkout(@PathVariable("id") Long workoutId,
                                       @RequestParam("exerciseId") Long exerciseId) {
        Workout workout = workoutRepository.findById(workoutId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid workout Id:" + workoutId));
        Exercise exercise = exerciseRepository.findById(exerciseId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid exercise Id:" + exerciseId));

        WorkoutExercise we = new WorkoutExercise(workout, exercise);
        workoutExerciseRepository.save(we);

        return "redirect:/";
    }

    @PostMapping("/workoutexercise/delete/{id}")
    // Method to delete a workout exercise
    public String deleteWorkoutExercise(@PathVariable("id") Long id) {
        WorkoutExercise we = workoutExerciseRepository.findById(id).orElse(null);
        if (we != null) {
            workoutExerciseRepository.delete(we);
            return "redirect:/";
        }
        return "redirect:/";
    }
}