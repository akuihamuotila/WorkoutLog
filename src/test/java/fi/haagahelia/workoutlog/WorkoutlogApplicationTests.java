package fi.haagahelia.workoutlog;

import fi.haagahelia.workoutlog.web.WorkoutController;
import fi.haagahelia.workoutlog.domain.WorkoutRepository;
import fi.haagahelia.workoutlog.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = {
		// This is a test configuration for an in-memory H2 database
        "spring.datasource.url=jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
class WorkoutlogApplicationTests {

    @Autowired
    private WorkoutController workoutController;

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
		// This test checks if the application context loads successfully
		// and if the necessary beans are created.
        assertThat(workoutController).isNotNull();
        assertThat(workoutRepository).isNotNull();
        assertThat(userRepository).isNotNull();
    }
}