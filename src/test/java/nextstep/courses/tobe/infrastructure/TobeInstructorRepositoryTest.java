package nextstep.courses.tobe.infrastructure;

import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.courses.domain.Instructor;
import nextstep.courses.tobe.domain.TobeInstructorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.domain.InstructorTest.IN1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class TobeInstructorRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private TobeInstructorRepository instructorRepository;

    @BeforeEach
    void setUp() {
        instructorRepository = new TobeJdbcInstructorRepository(jdbcTemplate);
    }
    @Test
    void crud() {
        Instructor instructor = IN1;
        int freeSessionSavedCount = instructorRepository.save(instructor);
        assertThat(freeSessionSavedCount).isEqualTo(1);

        Instructor savedInstructor = instructorRepository.findById(1L);
        LOGGER.info("savedInstructor = {}", savedInstructor);
        LOGGER.info("instructor = {}", instructor);

        assertThat(instructor).isEqualTo(savedInstructor);
    }


    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from instructor");
        jdbcTemplate.execute("ALTER TABLE instructor ALTER COLUMN id RESTART WITH 1");
    }
}
