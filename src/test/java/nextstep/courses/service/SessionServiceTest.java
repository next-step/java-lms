package nextstep.courses.service;

import nextstep.courses.domain.*;
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

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private Student student;
    private Payment payment;

    @BeforeEach
    public void setUp() throws Exception {
        session = new Session(1L, new Course(1L), SessionStatus.RECRUITING, SessionType.PAID, new Money(10000L), new Capacity(30), new Enrollments(), LocalDate.now(), LocalDate.now().plusDays(7), SessionCoverImage.from("imagePath"), LocalDateTime.now(), LocalDateTime.now());
        student = new Student(NsUserTest.JAVAJIGI, new Enrollments());
        payment = new Payment("paymentId", 1L, NsUserTest.JAVAJIGI.getId(), 10000L);
    }

    @Test
    public void 특정_강의에_대한_수강신청() {
        when(sessionRepository.findById(session.getId())).thenReturn(Optional.of(session));

        assertThat(session.getEnrollments().count()).isEqualTo(0);
        assertThat(student.getEnrollments().count()).isEqualTo(0);

        sessionService.enroll(session.getId(), student, payment);

        assertThat(session.getEnrollments().count()).isEqualTo(1);
        assertThat(student.getEnrollments().count()).isEqualTo(1);
    }

}
