package nextstep.courses.service;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.courses.domain.attendee.FreeAttendees;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static nextstep.courses.domain.attendee.Approval.*;
import static nextstep.courses.domain.session.Recruitment.*;
import static nextstep.courses.domain.session.SessionStatus.*;
import static nextstep.fixture.NsUserFixture.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private AttendeeRepository attendeeRepository;

    @InjectMocks
    private SessionService sessionService;

    @DisplayName("수강 신청을 한다.")
    @Test
    void success_session_enroll() {
        Period period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        SessionInformation information = new SessionInformation(PREPARING, period, RECRUITING);
        FreeEnrollment enrollment = new FreeEnrollment(new FreeAttendees());
        EnrollmentSession session = new EnrollmentSession(1L, information, enrollment);
        Payment payment = new Payment("1", 1L, JAVAJIGI.getId(), 1000L);
        Student student = new Student(JAVAJIGI.getId());
        when(sessionRepository.findBySessionId(anyLong())).thenReturn(Optional.of(session));

        sessionService.enroll(payment, student, 1L);

        verify(attendeeRepository).save(any());
    }

    @DisplayName("강의가 존재하지 않는다면 예외가 발생한다.")
    @Test
    void throw_exception_if_session_does_not_exist() {
        Payment payment = new Payment("1", 1L, JAVAJIGI.getId(), 1000L);
        Student student = new Student(JAVAJIGI.getId());
        when(sessionRepository.findBySessionId(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.enroll(payment, student, 10L))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("수강 신청을 승인한다.")
    @Test
    void approve_enrollment() {
        Student student = new Student(JAVAJIGI.getId());
        Attendee attendee = new Attendee(JAVAJIGI.getId(), 1L, NOT_APPROVED);
        when(attendeeRepository.findByStudentIdAndSessionId(anyLong(), anyLong())).thenReturn(Optional.of(attendee));

        sessionService.approve(student, 1L);

        verify(attendeeRepository).update(any());
    }

    @DisplayName("존재하지 않는 수강신청을 승인할 때 예외가 발생한다.")
    @Test
    void throw_exception_if_enrollment_does_not_exist_for_approval() {
        Student student = new Student(JAVAJIGI.getId());
        when(attendeeRepository.findByStudentIdAndSessionId(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.approve(student, 10L))
                .isInstanceOf(NotFoundException.class);
    }

    @DisplayName("수강 신청을 취소한다.")
    @Test
    void cancel_enrollment() {
        Student student = new Student(JAVAJIGI.getId());
        Attendee attendee = new Attendee(JAVAJIGI.getId(), 1L, APPROVED);
        when(attendeeRepository.findByStudentIdAndSessionId(anyLong(), anyLong())).thenReturn(Optional.of(attendee));

        sessionService.cancel(student, 1L);

        verify(attendeeRepository).update(any());
    }

    @DisplayName("존재하지 않는 수강신청을 취소할 때 예외가 발생한다.")
    @Test
    void throw_exception_if_enrollment_does_not_exist_for_canceling() {
        Student student = new Student(JAVAJIGI.getId());
        when(attendeeRepository.findByStudentIdAndSessionId(anyLong(), anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> sessionService.cancel(student, 10L))
                .isInstanceOf(NotFoundException.class);
    }
}