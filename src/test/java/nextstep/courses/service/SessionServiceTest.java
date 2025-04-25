package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.fixture.FakeEnrollmentRepository;
import nextstep.courses.fixture.FakeSessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionServiceTest {
    private SessionService sessionService;

    private Session session;
    private Student student;
    private Payment payment;

    @BeforeEach
    public void setUp() {
        sessionService = new SessionService(new FakeSessionRepository(), new FakeEnrollmentRepository());

        session = Session.createPaidSession(new Money(10000L), new Capacity(30), LocalDate.now(), LocalDate.now().plusDays(1));
        sessionService.save(session);

        student = new Student(NsUserTest.JAVAJIGI);
        payment = new Payment("paymentId", 1L, NsUserTest.JAVAJIGI.getId(), 10000L);
    }

    @Test
    public void 특정_강의에_대한_수강신청() {
        session.startRecruitment();

        Enrollment enrollment = sessionService.enroll(session.getId(), student, payment);
        assertThat(enrollment.getSession()).isEqualTo(session);
        assertThat(enrollment.getStudent()).isEqualTo(student);
    }

}
