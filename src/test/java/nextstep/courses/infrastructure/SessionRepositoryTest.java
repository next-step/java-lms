package nextstep.courses.infrastructure;


import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Transactional
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;

    private Set<NsUser> students = Set.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    private SessionImage sessionImage = SessionImageTest.S1;

    private Long courseId = 3L;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate, sessionRepository);
    }

    @Test
    @DisplayName("무료 강의 db에 넣고 조회 테스트")
    void testFreeSession() {
        FreeSession freeSession = new FreeSession(1L, sessionImage, SessionStatus.RECRUIT, SessionDateTest.of(), students);
        sessionRepository.saveSession(freeSession, courseId);

        Session findSession = sessionRepository.findBySessionId(freeSession.getId(), FreeSession.class).orElse(null);

        assertThat(findSession.getId()).isEqualTo(1L);

        assertThat(findSession.getSessionImage().getId()).isEqualTo(sessionImage.getId());

        assertThat(findSession.getStudents()).hasSize(2)
                .extracting(NsUser::getUserId)
                .containsAll(students.stream().map(NsUser::getUserId).collect(Collectors.toUnmodifiableList()));
    }

    @Test
    @DisplayName("유료 강의 db에 넣고 조회 테스트")
    void testPaySession() {
        int amount = 1000;
        PaySession paySession = new PaySession(1L, sessionImage, SessionStatus.RECRUIT, SessionDateTest.of(), students, 2, amount);

        sessionRepository.saveSession(paySession, courseId);

        Session findSession = sessionRepository.findBySessionId(paySession.getId(), PaySession.class).orElse(null);

        assertThat(findSession.getId()).isEqualTo(1L);

        assertThat(findSession.getSessionImage().getId()).isEqualTo(sessionImage.getId());

        assertThat(findSession.getStudents()).hasSize(2)
                .extracting(NsUser::getUserId)
                .containsAll(students.stream().map(NsUser::getUserId).collect(Collectors.toUnmodifiableList()));
    }

    @Test
    @DisplayName("모든 세션 조회 테스트")
    void testSessions() {
        int amount = 1000;
        FreeSession freeSession = new FreeSession(1L, sessionImage, SessionStatus.RECRUIT, SessionDateTest.of(), students);
        sessionRepository.saveSession(freeSession, courseId);

        PaySession paySession = new PaySession(1L, sessionImage, SessionStatus.RECRUIT, SessionDateTest.of(), students, 2, amount);
        sessionRepository.saveSession(paySession, courseId);

        Sessions sessions = sessionRepository.findByCourseId(courseId);

        assertThat(sessions.getSessions()).hasSize(2)
                .extracting(Session::getId).contains(freeSession.getId(), paySession.getId());
    }


}
