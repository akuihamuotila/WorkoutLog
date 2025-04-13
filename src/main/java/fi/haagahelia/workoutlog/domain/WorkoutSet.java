package fi.haagahelia.workoutlog.domain;

import jakarta.persistence.*;

@Entity
public class WorkoutSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int reps;
    private double weight;

    @ManyToOne
    @JoinColumn(name = "workout_exercise_id")
    private WorkoutExercise workoutExercise;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public WorkoutSet() {}
    // Constructor for creating a new WorkoutSet
    public WorkoutSet(int reps, double weight, WorkoutExercise workoutExercise, Exercise exercise) {
        this.reps = reps;
        this.weight = weight;
        this.workoutExercise = workoutExercise;
        this.exercise = exercise;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public WorkoutExercise getWorkoutExercise() { return workoutExercise; }
    public void setWorkoutExercise(WorkoutExercise workoutExercise) { this.workoutExercise = workoutExercise; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
}