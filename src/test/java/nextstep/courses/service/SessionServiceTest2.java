package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.courses.fixture.FakeEnrollmentRepository;
import nextstep.courses.fixture.FakeSessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class SessionServiceTest2 {
    private SessionService sessionService;

    private Session session;
    private Student student;
    private Payment payment;

    @BeforeEach
    public void setUp() {
        sessionService = new SessionService(new FakeSessionRepository(), new FakeEnrollmentRepository());
        session = new FakeSessionRepository().findById(1L).get();
        student = new Student(NsUserTest.JAVAJIGI, new Enrollments());
        payment = new Payment("paymentId", 1L, NsUserTest.JAVAJIGI.getId(), 10000L);
    }

    @Test
    public void 특정_강의에_대한_수강신청() {
        sessionService.enroll(session.getId(), student, payment);

        assertThat(session.getEnrollments().count()).isEqualTo(1);
        assertThat(student.getEnrollments().count()).isEqualTo(1);
    }

}
