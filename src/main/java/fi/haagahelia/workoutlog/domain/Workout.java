package fi.haagahelia.workoutlog.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "workout", cascade = CascadeType.ALL)
    private List<WorkoutExercise> workoutExercises;

    public Workout() {
        this.date = LocalDate.now();
    }
    // Constructor for creating a new workout for a specific user
    public Workout(User user) {
        this.user = user;
        this.date = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<WorkoutExercise> getWorkoutExercises() { return workoutExercises; }
    public void setWorkoutExercises(List<WorkoutExercise> workoutExercises) { this.workoutExercises = workoutExercises; }
}