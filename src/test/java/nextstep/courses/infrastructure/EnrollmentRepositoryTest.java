package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import nextstep.courses.domain.enrollment.EnrollmentStatus;
import nextstep.courses.domain.session.*;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@Rollback(value = false)
@JdbcTest
class EnrollmentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private EnrollmentRepository enrollmentRepository;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;

    private UserRepository userRepository;

    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, courseRepository);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate, sessionRepository, userRepository);

        Course course = new Course(1L, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), null);
        courseRepository.save(course);
        Session session = new Session(1L, course, new SessionImage("a", "/"),
                new SessionCapacity(0, 10), "TDD, 클린코드", SessionType.PAY,
                SessionStatus.READY, RecruitmentStatus.OPEN, LocalDateTime.now(), null);
        enrollment = new Enrollment(session, NsUserTest.JAVAJIGI, EnrollmentStatus.PENDING);
        sessionRepository.save(session);

        enrollmentRepository.save(enrollment);
    }

    @Test
    void 수강신청을_생성한다() {
        // given

        // when
        int count = enrollmentRepository.save(enrollment);
        // then
        assertThat(count).isEqualTo(1);
    }

    @Test
    void 수강신청을_수강신청ID로_조회한다() {
        // given & when
        Enrollment savedEnrollment = enrollmentRepository.findById(1L);

        // then
        assertThat(savedEnrollment.getId()).isEqualTo(1L);
    }

    @Test
    void 수강신청의_상태를_갱신한다() {
        // given
        enrollment.approve();

        // when
        enrollmentRepository.update(enrollment);

        // then
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }
}
