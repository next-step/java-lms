package nextstep.courses.service;

import nextstep.courses.code.ChargeType;
import nextstep.courses.code.EnrollStatus;
import nextstep.courses.code.ImageType;
import nextstep.courses.domain.DataStatus;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.repository.SessionRepository;
import nextstep.courses.domain.vo.*;
import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private Payment payment;

    @BeforeEach
    public void setUp() throws Exception {
        session = new Session(
                1L,
                new Course("course title", 0L),
                new SessionDetail("session title", NsUserTest.JAVAJIGI),
                new SessionPeriod(LocalDate.of(2024, 01, 01), LocalDate.of(2024, 12, 31)),
                new SessionImage(new ImageSpec(1024, 300, 200, ImageType.JPEG), 0L),
                new Enrollment(EnrollStatus.OPEN, new Students(new ArrayList<>(List.of(NsUserTest.JAVAJIGI))), 2),
                new Charge(ChargeType.PAID, 100_000L),
                new DataStatus(0L));

        payment = new Payment(1L, 0L, 100_000L);
    }

    @Test
    @DisplayName("수강 신청 테스트")
    void enrollSessionTest() throws Exception {
        when(sessionRepository.findById(session.id())).thenReturn(Optional.of(session));

        sessionService.enrollSession(NsUserTest.SANJIGI, 1L, payment);

        assertThat(session.enrollment().students().count()).isEqualTo(2);
    }

    @Test
    @DisplayName("수강 신청 실패 테스트 - 이미 수강신청한 학생")
    void enrollSessionFailForAlreadyEnrolledTest() {
        when(sessionRepository.findById(session.id())).thenReturn(Optional.of(session));

        assertThatThrownBy(() -> {
            sessionService.enrollSession(NsUserTest.JAVAJIGI, 1L, payment);
                }).isInstanceOf(AlreadyEnrolledException.class)
                .hasMessage("이미 수강신청한 학생입니다.");
    }
}
