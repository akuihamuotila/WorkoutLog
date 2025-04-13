package fi.haagahelia.workoutlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.CommandLineRunner;
import fi.haagahelia.workoutlog.domain.UserRepository;
import fi.haagahelia.workoutlog.domain.Workout;
import fi.haagahelia.workoutlog.domain.WorkoutRepository;
import fi.haagahelia.workoutlog.domain.Exercise;
import fi.haagahelia.workoutlog.domain.ExerciseRepository;
import fi.haagahelia.workoutlog.domain.User;

@SpringBootApplication
public class WorkoutlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkoutlogApplication.class, args);
	}

    @Bean
    public CommandLineRunner demo(UserRepository urepository, BCryptPasswordEncoder encoder) {
        return (args) -> {
            if (urepository.findByUsername("user1") == null) {
                urepository.save(new User("user1",
                    encoder.encode("userpass"), "USER"));
            }
            if (urepository.findByUsername("admin") == null) {
                urepository.save(new User("admin",
                    encoder.encode("adminpass"), "ADMIN"));
            }
        };
    }

	@Bean
	public CommandLineRunner data(UserRepository urepo, WorkoutRepository wrepo, BCryptPasswordEncoder encoder) {
		return (args) -> {
			if (urepo.findByUsername("user1") == null) {
				User user = new User("user1", encoder.encode("userpass"), "USER");
				urepo.save(user);
				wrepo.save(new Workout(user)); // treeni tälle käyttäjälle
			}
		};
	}

    @Bean
    public CommandLineRunner loadData(ExerciseRepository exerciseRepository) {
        return (args) -> {
            if (exerciseRepository.count() == 0) {
                exerciseRepository.save(new Exercise("Bench Press", "push"));
                exerciseRepository.save(new Exercise("Deadlift", "pull"));
                exerciseRepository.save(new Exercise("Squat", "legs"));
            }
        };
    }
}