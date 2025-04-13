package fi.haagahelia.workoutlog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkout(Workout workout);
}