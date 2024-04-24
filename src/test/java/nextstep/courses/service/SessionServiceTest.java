package nextstep.courses.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionTest;
import nextstep.payments.domain.Payment;

@Transactional
@SpringBootTest
class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("새로운 강의를 생성한다.")
    void Save_NewSession() {
        final Session session = SessionTest.session();
        final Long savedSessionId = sessionService.save(session);
        final Session savedSession = sessionService.findById(savedSessionId);

        assertThat(savedSession.id())
                .isEqualTo(savedSessionId);
    }

    @Test
    @DisplayName("수강 신청 시 현재 수강 인원이 증가한다.")
    void Enroll_IncreaseCurrentEnrollmentCount() {
        final Session session = SessionTest.session();
        final Long savedSessionId = sessionService.save(session);
        final Session savedSession = sessionService.findById(savedSessionId);

        sessionService.enroll(savedSessionId, new Payment(10000));

        final Session updatedSession = sessionService.findById(savedSessionId);
        assertThat(updatedSession.currentEnrollmentCount())
                .isEqualTo(savedSession.currentEnrollmentCount() + 1);
    }
}
