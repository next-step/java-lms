package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionTest;
import nextstep.payments.domain.Payment;

@JdbcTest
@Transactional
class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("새로운 강의를 생성한다.")
    void Save_NewSession() {
        final Session newSession = SessionTest.session();
        final long savedSessionId = sessionRepository.save(newSession);
        newSession.assignId(savedSessionId);

        assertThat(newSession)
                .isEqualTo(sessionRepository.findById(savedSessionId).get());
    }

    @Test
    @DisplayName("수강 신청을 하면, 현재 수강 신청 인원이 증가한다.")
    void Enroll_IncreaseEnrollmentCount() {
        Session session = SessionTest.session();
        final long savedSessionId = sessionRepository.save(session);

        session = sessionRepository.findById(savedSessionId).get();
        assertThat(session.currentEnrollmentCount())
                .isZero();

        session.enroll(new Payment(10000));
        sessionRepository.updateEnrollmentCount(session);

        session = sessionRepository.findById(savedSessionId).get();
        assertThat(session.currentEnrollmentCount())
                .isOne();
    }
}
