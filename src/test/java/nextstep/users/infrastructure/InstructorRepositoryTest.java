package nextstep.users.infrastructure;

import nextstep.users.domain.Instructor;
import nextstep.users.domain.InstructorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class InstructorRepositoryTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private InstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
        instructorRepository = new JdbcInstructorRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save")
    void save() {
        Instructor instructor = new Instructor(1L, 1L);
        int count = instructorRepository.save(instructor);
        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("select")
    void select() {
        Instructor instructor = new Instructor(1L, 1L);
        int count = instructorRepository.save(instructor);
        assertThat(count).isEqualTo(1);

        Instructor savedInstructor = instructorRepository.findById(1L);
        assertThat(savedInstructor).isEqualTo(instructor);
    }
}