package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionType;
import nextstep.courses.domain.fixture.SessionFixture;
import nextstep.courses.domain.student.SessionStudents;
import nextstep.courses.exception.SessionNotFoundException;
import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.engine.SessionRepository;
import nextstep.courses.infrastructure.engine.SessionStudentRepository;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static nextstep.courses.domain.fixture.IdFixture.NS_USER_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.NsUserFixture.nsUser;
import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionStudentsFixture.students;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionCoverImageRepository sessionCoverImageRepository;

    @Mock
    private SessionStudentRepository sessionStudentRepository;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private SessionService sessionService;

    @Test
    @DisplayName("[성공] 강의를 개설 한다.")
    void 강의_개설() {
        Session session = SessionFixture.freeSession();

        sessionService.establish(session);

        verify(sessionRepository).save(session);
        verify(sessionCoverImageRepository).saveAll(session.getCoverImages());
    }

    @Test
    @DisplayName("[성공] 무료 강의 수강신청을 한다.")
    void 무료_강의_수강신청() {
        // Given
        Session session = SessionFixture.freeSession();
        when(sessionRepository.findById(SESSION_ID)).thenReturn(Optional.of(session));

        SessionStudents students = students();
        when(sessionStudentRepository.findAllBySessionId(SESSION_ID)).thenReturn(students);

        Payment payment = payment(0L);
        when(paymentService.payment(SESSION_ID, NS_USER_ID, 0L)).thenReturn(payment);

        NsUser nsUser = nsUser();

        // When
        sessionService.enroll(SESSION_ID, nsUser);

        // Then
        verify(sessionStudentRepository).save(any());
    }

    @Test
    @DisplayName("[성공] 유료 강의 수강신청을 한다.")
    void 유료_강의_수강신청() {
        // Given
        int capacity = 50;
        long sessionFee = 800_000L;

        Session session = SessionFixture.paidSession(SessionType.PAID, capacity, sessionFee);
        when(sessionRepository.findById(SESSION_ID)).thenReturn(Optional.of(session));

        SessionStudents students = students();
        when(sessionStudentRepository.findAllBySessionId(SESSION_ID)).thenReturn(students);

        Payment payment = payment(sessionFee);
        when(paymentService.payment(SESSION_ID, NS_USER_ID, sessionFee)).thenReturn(payment);

        NsUser nsUser = nsUser();

        // When
        sessionService.enroll(SESSION_ID, nsUser);

        // Then
        verify(sessionStudentRepository).save(any());
    }

    @Test
    @DisplayName("[실패] 해당되는 강의가 없는 경우 SessionNotFoundException 예외가 발생한다.")
    void 수강신청_강의_조회_실패() {
        when(sessionRepository.findById(any())).thenReturn(Optional.empty());
        NsUser nsUser = nsUser();

        Assertions.assertThatExceptionOfType(SessionNotFoundException.class)
                        .isThrownBy(() -> sessionService.enroll(SESSION_ID, nsUser));
    }
}
