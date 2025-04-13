package fi.haagahelia.workoutlog.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class WorkoutExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @OneToMany(mappedBy = "workoutExercise", cascade = CascadeType.ALL)
    private List<WorkoutSet> sets;

    public WorkoutExercise() {}
    // Constructor for creating a new WorkoutExercise
    public WorkoutExercise(Workout workout, Exercise exercise) {
        this.workout = workout;
        this.exercise = exercise;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Workout getWorkout() { return workout; }
    public void setWorkout(Workout workout) { this.workout = workout; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public List<WorkoutSet> getSets() { return sets; }
    public void setSets(List<WorkoutSet> sets) { this.sets = sets; }
}