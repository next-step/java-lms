package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.users.domain.NextStepUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;

    private Course course = new Course("넥스트스텝", 1L);
    private Session session = new Session(
            course,
            new SessionEssentialInfo(
                    1,
                    "우하하하하",
                    0
            ),
            SessionType.FREE,
            SessionStatus.WAITING,
            new SessionPeriod(
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(5)
            )
    );
    private NextStepUser JAVAJIGI = new NextStepUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void create() {
        assertAll(
                () -> assertThat(session).isNotNull(),
                () -> assertThat(session.getCourse().getTitle()).isEqualTo("넥스트스텝"),
                () -> assertThat(session.getEssentialInfo().getCoverImage()).isEqualTo("우하하하하"),
                () -> assertThat(session.getType()).isEqualTo(SessionType.FREE),
                () -> assertThat(session.getStatus()).isEqualTo(SessionStatus.WAITING),
                () -> assertThat(session.getStatus()).isNotEqualTo(SessionStatus.RECRUIT)
        );
        ;
    }

    @ParameterizedTest
    @ValueSource(strings = "{a}")
    void addSignUpHistory(String input) {

        assertThatThrownBy(() -> {
            session.addSignUpHistory(new SignUpHistory(session, JAVAJIGI));
        }).hasMessageContaining("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");

        session = new Session(
                course,
                new SessionEssentialInfo(
                        1,
                        "우하하하하",
                        0
                ),
                SessionType.FREE,
                SessionStatus.RECRUIT,
                new SessionPeriod(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(5)
                )
        );

        assertThatThrownBy(() -> {
            session.addSignUpHistory(new SignUpHistory(session, JAVAJIGI));
        }).hasMessageContaining("최대 수강 인원을 초과할 수 없습니다.");

    }

    @Test
    void crud() {

        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);
        Course savedCourse = courseRepository.findById(1L);

        Session session = new Session(savedCourse,
                new SessionEssentialInfo(
                        1,
                        "우하하하하",
                        10
                ),
                SessionType.FREE,
                SessionStatus.WAITING,
                new SessionPeriod(
                        LocalDateTime.now(),
                        LocalDateTime.now().plusDays(5)
                )
        );
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session saveSession = sessionRepository.findById(1L);
        assertThat(saveSession.getEssentialInfo().getCoverImage()).isEqualTo(session.getEssentialInfo().getCoverImage());
        LOGGER.debug("saveSession : {}", saveSession);

    }

}
