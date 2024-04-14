package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcEnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;
    private EnrollmentRepository repository;

    @BeforeEach
    public void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        repository = new JdbcEnrollmentRepository(jdbcTemplate);
    }

    @DisplayName("CRUD 테스트")
    @Test
    void crud() {
        courseRepository.save(CourseTest.C1);
        sessionRepository.save(SessionTest.FREE_S1);
        Enrollment enrollment = new Enrollment(0L, SessionTest.FREE_S1.getId(), NsUserTest.JAVAJIGI.getId(), EnrollmentStatus.APPROVED);
        int count = repository.save(enrollment);
        assertThat(count).isEqualTo(1);
        Enrollment savedEnrollment = repository.findById(0L);
        assertThat(savedEnrollment.getId()).isEqualTo(enrollment.getId());
    }
}