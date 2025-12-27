package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.repository.EnrollmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class SessionServiceTest {

    @Autowired
    SessionService sessionService;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Test
    void 강의를_개설하고_조회하기() {
        ImageFile imageFile = new ImageFile(1024 * 1024, "png", 300, 200);
        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));


        Long sessionId = sessionService.createSession(imageFile, period, SessionStatus.RECRUITING, new PaidEnrollmentRule(50000, 10));

        Session found = sessionService.findSession(sessionId);

        assertThat(found.getSessionStatus())
                .isEqualTo(SessionStatus.RECRUITING.toString());
        assertThat(found.getPeriod()).isEqualTo(period);
    }

    @Test
    void 수강신청_성공() {
        ImageFile imageFile = new ImageFile(1024 * 1024, "png", 300, 200);
        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));
        Long sessionId = sessionService.createSession(imageFile, period, SessionStatus.RECRUITING, new PaidEnrollmentRule(50000, 10));

        sessionService.enroll(sessionId, 10L, new Money(50000));

        assertThat(enrollmentRepository.findBySessionId(sessionId)).hasSize(1);
    }
}