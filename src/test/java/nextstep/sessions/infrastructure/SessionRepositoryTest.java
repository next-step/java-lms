package nextstep.sessions.infrastructure;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import nextstep.sessions.domain.data.session.*;
import nextstep.sessions.domain.exception.NotFoundSessionException;
import nextstep.sessions.repository.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void 강의_생성_및_조회() {
        EnrollmentInfo enrollmentInfo = new EnrollmentInfo(
            new SessionType(PaidType.PAID, 800000L, 10),
            new SessionState(SessionRunningState.RUNNING, SessionRecruitingState.RECRUITING)
        );
        OpenInfo openInfo = new OpenInfo(new Duration(LocalDateTime.now(), LocalDateTime.now().plusDays(10)));
        Session session = new Session(enrollmentInfo, openInfo);

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1)
            .orElseThrow(() -> new NotFoundSessionException("강의 정보가 없습니다."));
        assertThat(savedSession.sessionRunningState()).isEqualTo(session.sessionRunningState());
        assertThat(savedSession.sessionRecruitingState()).isEqualTo(session.sessionRecruitingState());
    }
}
