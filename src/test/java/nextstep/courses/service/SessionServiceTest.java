package nextstep.courses.service;

import nextstep.courses.domain.Amount;
import nextstep.courses.domain.Apply;
import nextstep.courses.domain.ApplyRepository;
import nextstep.courses.domain.ApplyStatus;
import nextstep.courses.domain.CoverImage;
import nextstep.courses.domain.CoverImageFileName;
import nextstep.courses.domain.CoverImagePixel;
import nextstep.courses.domain.CoverImageSize;
import nextstep.courses.domain.CoverImages;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionDuration;
import nextstep.courses.domain.SessionEnrolment;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatusType;
import nextstep.courses.domain.SessionStudent;
import nextstep.courses.domain.Students;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ApplyRepository applyRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    @DisplayName("정상적인 신청이라면 강의는 성공적으로 신청된다.")
    void enrolment() {
        Session session = mockSession(100, SessionStatusType.READY, 10_000L, true);

        when(sessionRepository.findById(1L)).thenReturn(session);
        when(userRepository.findById(1L)).thenReturn(NsUserTest.JAVAJIGI);

        sessionService.enrolment(new Payment("1", 1L, 1L, 10_000L));

        verify(applyRepository).save(new Apply(session, NsUserTest.JAVAJIGI, ApplyStatus.APPLYING));
    }

    @Test
    @DisplayName("유료강의의 수강인원이 모두 찬 경우 수강신청이 불가능하다.")
    void enrolment_수강인원_max() {
        Session session = mockSession(0, SessionStatusType.READY, 10_000L, false);

        when(sessionRepository.findById(1L)).thenReturn(session);
        when(userRepository.findById(1L)).thenReturn(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> sessionService.enrolment(new Payment("1", 1L, 1L, 10_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최대 수강 인원이 모두 찼습니다.");
    }

    @Test
    @DisplayName("유료강의의 신청시 결제한 금액이 강의금액과 불일치 하다면 강의 수강신청이 불가능하다.")
    void enrolment_강의금액_불일치() {
        Session session = mockSession(100, SessionStatusType.READY, 10_000L, false);

        when(sessionRepository.findById(1L)).thenReturn(session);
        when(userRepository.findById(1L)).thenReturn(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> sessionService.enrolment(new Payment("1", 1L, 1L, 1_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제금액과 강의금액이 맞지 않습니다.");
    }

    @Test
    @DisplayName("강의가 비모집중 상태라면 강의신청이 불가능하다.")
    void enrolment_강의_비모집중() {
        Session session = mockSession(100, SessionStatusType.END, 10_000L, false);

        when(sessionRepository.findById(1L)).thenReturn(session);
        when(userRepository.findById(1L)).thenReturn(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> sessionService.enrolment(new Payment("1", 1L, 1L, 1_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 강의가 모집중인 상태가 아닙니다.");
    }

    private Session mockSession(int maxStudentCount, SessionStatusType sessionStatusType, Long amount, boolean isFree) {
        SessionDuration sessionDuration = new SessionDuration(LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(3));
        CoverImage coverImage = new CoverImage(1L, new CoverImageFileName("test.png"), new CoverImageSize(300), new CoverImagePixel(300, 200));
        SessionStudent sessionStudent = new SessionStudent(new Students(), maxStudentCount);
        SessionEnrolment sessionEnrolment = new SessionEnrolment(sessionStudent, sessionStatusType, new Amount(amount), isFree);

        return new Session(1L, sessionDuration, sessionEnrolment, new CoverImages(List.of(coverImage)));
    }
}
