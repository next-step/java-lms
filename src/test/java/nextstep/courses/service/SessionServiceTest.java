package nextstep.courses.service;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageExtension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

        paidSession = new PaidSession(1L, 1L, SessionBody.of("유료 세션", period, coverImage),
                SessionEnrollment.of(SessionStatus.OPEN), 10000L, 100);

        freeSession = new FreeSession(2L, 1L, SessionBody.of("무료 세션", period, coverImage),
                SessionEnrollment.of(SessionStatus.OPEN));
    }

    @DisplayName("무료 강의에 등록할 수 있다.")
    @Test
    void enrollFreeSessionTest() {
        when(sessionRepository.findById(freeSession.getId())).thenReturn(Optional.of(freeSession));

        sessionService.enroll(NsUserTest.JAVAJIGI, freeSession.getId(), new Payment("1", freeSession.getId(), NsUserTest.JAVAJIGI.getId(), 0L));

        assertThat(freeSession.getEnrolledUsers()).contains(NsUserTest.JAVAJIGI);
    }

    @DisplayName("유료 강의에 등록할 수 있다.")
    @Test
    void enrollPaidSessionTest() {
        when(sessionRepository.findById(paidSession.getId())).thenReturn(Optional.of(paidSession));

        sessionService.enroll(NsUserTest.JAVAJIGI, paidSession.getId(), new Payment("1", paidSession.getId(), NsUserTest.JAVAJIGI.getId(), 10000L));

        assertThat(paidSession.getEnrolledUsers()).contains(NsUserTest.JAVAJIGI);
    }


}
