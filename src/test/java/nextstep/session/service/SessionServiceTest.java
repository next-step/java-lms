package nextstep.session.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import nextstep.session.CannotEnrollException;
import nextstep.session.domain.Session;
import nextstep.session.domain.SessionCoverImageRepository;
import nextstep.session.domain.SessionProgressStatus;
import nextstep.session.domain.SessionRepository;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SessionCoverImageRepository sessionCoverImageRepository;

    @Mock
    private SessionStudentRepository sessionStudentRepository;

    @InjectMocks
    private SessionService sessionService;

    private Session session;
    private NsUser user;

    @BeforeEach
    public void setUp() {
        user = new NsUser(1L, "somin", "1111", "박소민", "test@naver.com");
        session = Session.createSessionWithProgressStatusAndFee(SessionProgressStatus.IN_PROGRESS,
            50000);
    }

    @Test
    public void 강의_상태에_따른_수강_신청_불가능() {
        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        session.changeEnrollmentStatusClosed();
        assertThatThrownBy(() -> {
            sessionService.enroll(session.getSessionId(), user, 50000);
        }).isInstanceOf(CannotEnrollException.class).hasMessageContaining("현재 모집중인 강의가 아닙니다.");
    }

    @Test
    public void 수강_비용이_일치하지_않으면_수강_신청_불가능() {
        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        assertThatThrownBy(() -> {
            sessionService.enroll(session.getSessionId(), user, 10000);
        }).isInstanceOf(CannotEnrollException.class).hasMessageContaining("수강료와 지불금액이 일치하지 않습니다.");
    }

    @Test
    public void 수강_신청() {
        when(sessionRepository.findById(session.getSessionId())).thenReturn(session);
        sessionService.enroll(session.getSessionId(), user, 50000);
        assertThat(session.enrolledStudentCount()).isEqualTo(1);

    }
}
