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
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    public WorkoutSet() {}

    public WorkoutSet(int reps, double weight, Exercise exercise, Workout workout) {
        this.reps = reps;
        this.weight = weight;
        this.exercise = exercise;
        this.workout = workout;
    }

    // getterit ja setterit
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getReps() { return reps; }
    public void setReps(int reps) { this.reps = reps; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }

    public Workout getWorkout() { return workout; }
    public void setWorkout(Workout workout) { this.workout = workout; }
}