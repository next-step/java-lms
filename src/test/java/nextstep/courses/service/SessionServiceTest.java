package nextstep.courses.service;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageExtension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session paidSession;
    private Session freeSession;

    @BeforeEach
    void setUp() {
        CoverImage coverImage = CoverImage.of("nextstep", ImageSize.of(1000), ImageExtension.JPG.name(), ImageDimension.of(300, 200));
        SessionPeriod period = SessionPeriod.of(LocalDateTime.now(), LocalDateTime.now().plusDays(7));

        paidSession = new PaidSession(1L, 1L, SessionBody.of("유료 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING), 10000L, 100);

        freeSession = new FreeSession(2L, 1L, SessionBody.of("무료 세션", period, List.of(coverImage)),
                SessionEnrollment.of(ProgressStatus.IN_PROGRESS, RecruitmentStatus.NOT_RECRUITING));
    }

    @DisplayName("무료 강의에 등록할 수 있다.")
    @Test
    void enrollFreeSessionTest() {
        when(sessionRepository.findById(freeSession.getId())).thenReturn(Optional.of(freeSession));
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), freeSession.getId());

        sessionService.enroll(student.getNsUserId(), freeSession.getId(), new Payment("1", freeSession.getId(), NsUserTest.JAVAJIGI.getId(), 0L));

        assertThat(freeSession.getEnrolledStudents()).contains(student);
    }

    @DisplayName("유료 강의에 등록할 수 있다.")
    @Test
    void enrollPaidSessionTest() {
        when(sessionRepository.findById(paidSession.getId())).thenReturn(Optional.of(paidSession));
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), paidSession.getId());

        sessionService.enroll(student.getNsUserId(), paidSession.getId(), new Payment("1", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10000L));

        assertThat(paidSession.getEnrolledStudents()).contains(student);
    }

    @DisplayName("수강신청 시 강의를 찾지 못하면 예외가 발생한다.")
    @Test
    void throwsExceptionWhenSessionNotFound() {
        long invalidSessionId = 999L;
        when(sessionRepository.findById(invalidSessionId)).thenReturn(Optional.empty());

        assertThatThrownBy(
                () ->   sessionService.enroll(NsUserTest.JAVAJIGI.getId(), invalidSessionId, new Payment("1", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10000L))
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 강의입니다.");
    }


    @DisplayName("수강신청된 사용자의 수강을 승인할 수 있다.")
    @Test
    void approveTest() {
        when(sessionRepository.findById(paidSession.getId())).thenReturn(Optional.of(paidSession));
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), paidSession.getId());

        sessionService.enroll(student.getNsUserId(), paidSession.getId(), new Payment("1", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10000L));
        assertThat(paidSession.getEnrolledStudents()).contains(student);

        assertThat(getSingleStudent().isApproved()).isFalse();

        sessionService.approve(student.getNsUserId(), paidSession.getId());

        assertThat(getSingleStudent().isApproved()).isTrue();
    }

    @DisplayName("수강신청된 수강생의 수강을 취소할 수 있다.")
    @Test
    void rejectTest() {
        when(sessionRepository.findById(paidSession.getId())).thenReturn(Optional.of(paidSession));
        Student student = Student.of(NsUserTest.JAVAJIGI.getId(), paidSession.getId());
        sessionService.enroll(student.getNsUserId(), paidSession.getId(), new Payment("1", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10000L));
        assertThat(paidSession.getEnrolledStudents()).contains(student);
        assertThat(getSingleStudent().isRejected()).isFalse();

        sessionService.reject(student.getNsUserId(), paidSession.getId());

        assertThat(getSingleStudent().isRejected()).isTrue();
    }

    private Student getSingleStudent() {
        assertThat(paidSession.getEnrolledStudents()).hasSize(1);
        return  paidSession.getEnrolledStudents().iterator().next();
    }

}
