package nextstep.sessions.infrastructure;

import nextstep.common.Period;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionApprovalStatus;
import nextstep.sessions.domain.SessionCharge;
import nextstep.sessions.domain.SessionImageTest;
import nextstep.sessions.domain.SessionImages;
import nextstep.sessions.domain.SessionProgressStatus;
import nextstep.sessions.domain.SessionRecruitmentStatus;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionStatus;
import nextstep.sessions.domain.SessionStudent;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @DisplayName("생성한 강의를 저장하고, 그 강의를 가져올 수 있다.")
    @Test
    void crud() {
        Session session = new Session(
                "강의",
                new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
                new SessionImages(List.of(SessionImageTest.IMAGE_JPG, SessionImageTest.IMAGE_PNG)),
                new SessionCharge(true, 1000, 3),
                new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING),
                userRepository.findById(1L).get());
        long count = sessionRepository.save(session);
        assertThat(count).isEqualTo(2);

        Session savedSession = sessionRepository.findById(2L);
        assertThat(session.getName()).isEqualTo(savedSession.getName());
        LOGGER.debug("Session: {}", savedSession);
    }

    @DisplayName("강의를 신청하고 그 학생을 승인하면 승인 상태로 상태로 변경할 수 있다.")
    @Test
    void approvalTest() {
        Session session = new Session(
                "강의",
                new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
                new SessionImages(List.of(SessionImageTest.IMAGE_JPG, SessionImageTest.IMAGE_PNG)),
                new SessionCharge(true, 1000, 3),
                new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING),
                userRepository.findById(1L).get());
        SessionStudent sessionStudent = session.enroll(NsUserTest.JAVAJIGI);
        sessionRepository.enroll(session, sessionStudent);

        session.approve(sessionStudent, NsUser.ADMIN_USER);
        sessionRepository.approvalStudent(session, sessionStudent);

        SessionStudent savedStudent = sessionRepository.studentFindBySessionIdAndUserId(session.getId(), NsUserTest.JAVAJIGI.getId());
        assertThat(savedStudent.getStatus()).isEqualTo(SessionApprovalStatus.APPROVAL);
    }

    @DisplayName("강의를 신청하고 그 학생을 취소하면 취소 상태로 상태를 변경할 수 있다.")
    @Test
    void cancelTest() {
        Session session = new Session(
                "강의",
                new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31)),
                new SessionImages(List.of(SessionImageTest.IMAGE_JPG, SessionImageTest.IMAGE_PNG)),
                new SessionCharge(true, 1000, 3),
                new SessionStatus(SessionProgressStatus.PREPARING, SessionRecruitmentStatus.RECRUITING),
                userRepository.findById(1L).get());
        SessionStudent sessionStudent = session.enroll(NsUserTest.JAVAJIGI);
        sessionRepository.enroll(session, sessionStudent);

        session.cancel(sessionStudent, NsUser.ADMIN_USER);
        sessionRepository.cancelStudent(session, sessionStudent);

        SessionStudent savedStudent = sessionRepository.studentFindBySessionIdAndUserId(session.getId(), NsUserTest.JAVAJIGI.getId());
        assertThat(savedStudent.getStatus()).isEqualTo(SessionApprovalStatus.CANCEL);
    }
}
