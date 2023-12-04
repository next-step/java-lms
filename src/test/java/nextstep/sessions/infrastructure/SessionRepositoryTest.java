package nextstep.sessions.infrastructure;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.*;
import nextstep.sessions.domain.exception.SessionsException;
import nextstep.sessions.repository.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }


    @Test
    void 강의_생성() {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo(new SessionType(PaidType.PAID, 800000L, 10), SessionState.RECRUITING);
        OpenInfo openInfo = new OpenInfo(new Duration(LocalDateTime.now(), LocalDateTime.now().plusDays(10)));
        SessionInfo sessionInfo = new SessionInfo(enrollmentInfo, openInfo);
        Session session = new Session(sessionInfo);

        int count = sessionRepository.saveSession(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findSessionBySessionId(1);
        assertThat(savedSession.sessionInfo().enrollmentInfo().sessionState()).isEqualTo(session.sessionInfo().enrollmentInfo().sessionState());
    }

    @Test
    void 커버_이미지_생성() {
        CoverImage coverImage = new CoverImage("image.png", 102400, 300, 200);

        int count = sessionRepository.saveCoverImage(1, coverImage);
        assertThat(count).isEqualTo(1);

        CoverImage savedCoverImage = sessionRepository.findCoverImageBySessionId(1);
        assertThat(savedCoverImage.imageType()).isEqualTo(coverImage.imageType());
    }

    @Test
    void 유료_강의_수강_신청_성공() {
        int sessionId = 6;
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        List<Registration> registrations = sessionRepository.findAllRegistrations(sessionId);
        Session sessionWithRegistrations = new Session(session.sessionInfo(), registrations);

        sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.NEWJIGI, new Payment(1L, 2L, 3L, 800000L)));
        List<Registration> current_registrations = sessionRepository.findAllRegistrations(sessionId);
        assertThat(current_registrations).hasSize(registrations.size() + 1);
    }

    @Test
    void 무료_강의_수강_신청_성공() {
        int sessionId = 7;
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        List<Registration> registrations = sessionRepository.findAllRegistrations(sessionId);
        Session sessionWithRegistrations = new Session(session.sessionInfo(), registrations);

        sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.NEWJIGI, new Payment(1L, 2L, 3L, 800000L)));
        List<Registration> current_registrations = sessionRepository.findAllRegistrations(sessionId);
        assertThat(current_registrations).hasSize(registrations.size() + 1);
    }

    @Test
    void 수강_신청_실패_인원_초과() {
        int sessionId = 10;
        Session sessionWithRegistrations = getSession(sessionId);

        assertThatThrownBy(() -> sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.NEWJIGI, new Payment(1L, 2L, 3L, 800000L))))
            .isInstanceOf(SessionsException.class)
            .hasMessage("강의 최대 인원을 초과했습니다.");
    }

    @Test
    void 수강_신청_실패_모집중_아님() {
        int sessionId = 8;
        Session sessionWithRegistrations = getSession(sessionId);

        assertThatThrownBy(() -> sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.NEWJIGI, new Payment(1L, 2L, 3L, 800000L))))
            .isInstanceOf(SessionsException.class)
            .hasMessage("모집중이 아닌 강의입니다.");
    }

    @Test
    void 수강_신청_실패_중복_신청() {
        int sessionId = 9;
        Session sessionWithRegistrations = getSession(sessionId);

        assertThatThrownBy(() -> sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.SANJIGI, new Payment(1L, 2L, 3L, 800000L))))
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미 수강신청된 사용자 입니다.");
    }

    @Test
    void 수강_신청_실패_수강료_결제_금액_불일치() {
        int sessionId = 9;
        Session sessionWithRegistrations = getSession(sessionId);

        assertThatThrownBy(() -> sessionRepository.saveRegistration(sessionId, sessionWithRegistrations.registration(NsUserTest.SANJIGI, new Payment(1L, 2L, 3L, 790000L))))
            .isInstanceOf(SessionsException.class)
            .hasMessage("수강료와 결제한 금액이 다릅니다.");
    }

    private Session getSession(int sessionId) {
        Session session = sessionRepository.findSessionBySessionId(sessionId);
        List<Registration> registrations = sessionRepository.findAllRegistrations(sessionId);
        return new Session(session.sessionInfo(), registrations);
    }
}
