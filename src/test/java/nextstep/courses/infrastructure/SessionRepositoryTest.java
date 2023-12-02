package nextstep.courses.infrastructure;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.RecruitmentStatusType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudent;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setup() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
    }

    @Test
    @DisplayName("Session 데이터 생성 테스트")
    void create() {
        Session session = createSession(100, SessionStatusType.ONGOING, RecruitmentStatusType.RECRUITING, 10_000L, false);

        int actual = sessionRepository.save(session);

        Assertions.assertThat(actual).isOne();
    }

    @Test
    @DisplayName("Session 데이터 조회 테스트")
    void read() {
        Session expected = createSession(100, SessionStatusType.ONGOING, RecruitmentStatusType.RECRUITING, 10_000L, false);
        sessionRepository.save(expected);

        Session actual = sessionRepository.findById(1L);

        assertAll(
                () -> assertThat(actual.courseId()).isEqualTo(expected.courseId()),
                () -> assertThat(actual.totalStudentCount()).isEqualTo(expected.totalStudentCount()),
                () -> assertThat(actual.sessionStatus()).isEqualTo(expected.sessionStatus()),
                () -> assertThat(actual.recruitmentStatus()).isEqualTo(expected.recruitmentStatus()),
                () -> assertThat(actual.sessionAmount()).isEqualTo(expected.sessionAmount()),
                () -> assertThat(actual.isFree()).isEqualTo(expected.isFree()));
    }

    @Test
    @DisplayName("Session 데이터 갱신 테스트")
    void update() {
        Session session = createSession(100, SessionStatusType.ONGOING, RecruitmentStatusType.RECRUITING, 10_000L, false);
        sessionRepository.save(session);

        Session updateSession = updateSession(1L, 1000, SessionStatusType.READY, RecruitmentStatusType.RECRUITING, 2_000L, false);
        int actual = sessionRepository.update(updateSession);

        assertThat(actual).isOne();
    }

    @Test
    @DisplayName("Session 데이터 삭제 테스트")
    void delete() {
        Session session = createSession(100, SessionStatusType.ONGOING, RecruitmentStatusType.RECRUITING, 10_000L, false);
        sessionRepository.save(session);

        int actual = sessionRepository.delete(1L);

        assertThat(actual).isOne();
    }

    private Session createSession(int maxStudentCount, SessionStatusType sessionStatusType, RecruitmentStatusType recruitmentStatusType, Long amount, boolean isFree) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        SessionStudent sessionStudent = new SessionStudent(maxStudentCount);
        SessionStatus sessionStatus = new SessionStatus(sessionStatusType, recruitmentStatusType);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, sessionStatus, new Amount(amount), isFree);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages());
    }

    private Session updateSession(Long id, int maxStudentCount, SessionStatusType sessionStatusType, RecruitmentStatusType recruitmentStatusType, Long amount, boolean isFree) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        SessionStudent sessionStudent = new SessionStudent(maxStudentCount);
        SessionStatus sessionStatus = new SessionStatus(sessionStatusType, recruitmentStatusType);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, sessionStatus, new Amount(amount), isFree);

        return new Session(id, 1L, sessionDuration, sessionEnrolment, new CoverImages());
    }
}
