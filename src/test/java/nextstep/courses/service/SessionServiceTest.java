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
        ImageFiles imageFiles = new ImageFiles(new ImageFile(1024 * 1024, "png", 300, 200));

        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        Long sessionId = sessionService.createSession(imageFiles, period, SessionRecruitingStatus.RECRUITING, SessionProgressStatus.READY, new PaidEnrollmentRule(50000, 10));

        Session found = sessionService.findSession(sessionId);

        assertThat(found.getRecruitingStatus())
                .isEqualTo(SessionRecruitingStatus.RECRUITING.toString());
        assertThat(found.getPeriod()).isEqualTo(period);
    }

    @Test
    void 수강신청_성공() {
        ImageFiles imageFiles = new ImageFiles(new ImageFile(1024 * 1024, "png", 300, 200));

        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        Long sessionId = sessionService.createSession(imageFiles, period, SessionRecruitingStatus.RECRUITING, SessionProgressStatus.READY, new PaidEnrollmentRule(50000, 10));

        sessionService.enroll(sessionId, 10L, new Money(50000));

        assertThat(enrollmentRepository.findBySessionId(sessionId)).hasSize(1);
    }

    @Test
    void 수강신청_후_승인된다() {

        ImageFiles imageFiles = new ImageFiles(new ImageFile(1024 * 1024, "png", 300, 200));

        SessionPeriod period = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        Long sessionId = sessionService.createSession(imageFiles, period, SessionRecruitingStatus.RECRUITING, SessionProgressStatus.READY, new PaidEnrollmentRule(50000, 10));

        // 수강 신청
        sessionService.enroll(sessionId, 1L, new Money(50000));

        Enrollment enrollment = enrollmentRepository.findBySessionId(sessionId).get(0);

        sessionService.selectEnrollment(sessionId, enrollment.getId());
        sessionService.approveEnrollment(sessionId, enrollment.getId());

        Enrollment approved = enrollmentRepository.findById(enrollment.getId());

        assertThat(approved.getEnrollmentStatus())
                .isEqualTo(EnrollmentStatus.APPROVED.toString());
    }
}