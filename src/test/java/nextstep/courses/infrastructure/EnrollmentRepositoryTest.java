package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static nextstep.courses.infrastructure.TestUtil.autoincrementReset;
import static nextstep.courses.infrastructure.TestUtil.createDefaultTddSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class EnrollmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;
    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;
    private ImageRepository imageRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);
        Course savedCourse = courseRepository.findById(1L).get();

        final Session tddSession = createDefaultTddSession();
        int count = sessionRepository.save(savedCourse.id(), tddSession);
        Optional<NsUser> javajigi = userRepository.findByUserId("javajigi");

        final Enrollment enrollment = new Enrollment(javajigi.get().getId(), 1L);
        enrollmentRepository.save(enrollment);
        assertThat(count).isEqualTo(1);
        Enrollment savedEnrollment = enrollmentRepository.findById(1L).get();
        assertThat(savedEnrollment.nsUserId()).isEqualTo(1L);
        assertThat(savedEnrollment.sessionId()).isEqualTo(1L);
    }
}
