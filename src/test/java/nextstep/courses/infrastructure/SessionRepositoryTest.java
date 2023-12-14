package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.type.ImageExtension;
import nextstep.courses.type.MaxRegister;
import nextstep.courses.type.SessionDuration;
import nextstep.payments.domain.Payment;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, courseRepository, userRepository);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long courseId = courseRepository.save(course);
        course = courseRepository.findById(courseId);

        SessionImage coverImage = new SessionImage("/a", 500, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-11T16:56:00", "2023-12-13T12:00:00");
        Session session = Session.createPaidSession(1L, course, coverImage, duration, MaxRegister.of(10), 100);

        long pk = sessionRepository.save(session);
        assertThat(pk).isNotEqualTo(0L);

        Session savedSession = sessionRepository.findById(pk);
        assertThat(session).isEqualTo(savedSession);

        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void embeddableTest() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long coursePk = courseRepository.save(course);
        course = courseRepository.findById(coursePk);

        SessionImage coverImage = new SessionImage("/a", 500, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-11T16:56:00", "2023-12-13T12:00:00");
        Session session = Session.createPaidSession(1L, course, coverImage, duration, MaxRegister.of(10), 100);

        long sessionKey = sessionRepository.save(session);

        Session foundedSession = sessionRepository.findById(sessionKey);

        assertThat(foundedSession.getCoverImage().getSize().getWidth()).isEqualTo(300);
    }

    @Test
    void registeredUsersTest() {
        NsUser myUser1 = new NsUser(null, "mymy", "mypass", "myname", "mymail");
        NsUser myUser2 = new NsUser(null, "meme", "mepass", "mename", "memail");
        long user1Pk = userRepository.save(myUser1);
        long user2Pk = userRepository.save(myUser2);
        myUser1 = userRepository.findById(user1Pk);
        myUser2 = userRepository.findById(user2Pk);

        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long coursePk = courseRepository.save(course);
        course = courseRepository.findById(coursePk);

        SessionImage coverImage = new SessionImage("/a", 500, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-11T16:56:00", "2023-12-13T12:00:00");
        Session session = Session.createPaidSession(1L, course, coverImage, duration, MaxRegister.of(10), 100);
        session.registerUser(myUser1, new Payment(null, session, myUser1, 100L));
        session.registerUser(myUser2, new Payment(null, session, myUser2, 100L));
        long sessionPk = sessionRepository.save(session);

        Session savedSession = sessionRepository.findById(sessionPk);

        assertThat(savedSession.getRegisteredUsers().toList())
                .hasSameElementsAs(List.of(myUser1, myUser2));
    }
}
