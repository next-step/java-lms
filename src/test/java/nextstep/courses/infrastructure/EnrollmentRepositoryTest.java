package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.entity.Enrollment;
import nextstep.courses.domain.entity.NsCourse;
import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.repository.CourseRepository;
import nextstep.courses.domain.repository.EnrollmentRepository;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.infrastructure.course.JdbcCourseRepository;
import nextstep.courses.infrastructure.enrollment.JdbcEnrollmentRepository;
import nextstep.courses.infrastructure.session.JdbcSessionRepository;
import nextstep.users.domain.NsUser;

@JdbcTest
public class EnrollmentRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;
    private EnrollmentRepository enrollmentRepository;

    private NsSession session;
    private NsCourse course;
    private NsUser user;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate);

        LocalDate startDate = LocalDate.of(2023, Month.NOVEMBER, 29);
        LocalDate endDate = LocalDate.of(2023, Month.NOVEMBER, 30);
        NsSession nsSession = NsSession.freeOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               "free",
                                               "open",
                                               startDate,
                                               endDate);
        sessionRepository.save(nsSession);
        courseRepository.save(new NsCourse("TDD, 클린 코드 with Java", 1L));

        session = sessionRepository.findById(1L);
        course = courseRepository.findById(1L);
        user = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    }

    @Test
    void save_enrollment() {
        int savedCount = enrollmentRepository.save(course, user, session);
        assertThat(savedCount).isEqualTo(1);
    }

    @Test
    void find_enrollment() {
        enrollmentRepository.save(course, user, session);
        Enrollment result = enrollmentRepository.findById(1L);

        assertThat(course.getId()).isEqualTo(result.getNsCourse().getId());
        assertThat(user.getId()).isEqualTo(result.getNsUser().getId());
        assertThat(session.getId()).isEqualTo(result.getNsSession().getId());

        LOGGER.debug("course id: {}", result.getNsCourse().getId());
        LOGGER.debug("user id: {}", result.getNsUser().getId());
        LOGGER.debug("session id: {}", result.getNsSession().getId());
    }
}
