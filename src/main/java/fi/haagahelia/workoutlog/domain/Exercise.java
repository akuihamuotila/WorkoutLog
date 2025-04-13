package fi.haagahelia.workoutlog.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;

    // OneToMany relationship with WorkoutSet
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL)
    private List<WorkoutSet> sets;

    public Exercise() {}
    // Constructor for creating a new exercise
    public Exercise(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public List<WorkoutSet> getSets() { return sets; }
    public void setSets(List<WorkoutSet> sets) { this.sets = sets; }
}