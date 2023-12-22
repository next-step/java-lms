package nextstep.courses.domain.course.service;

import nextstep.courses.domain.course.session.SessionRecruitStatus;
import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.domain.course.session.SessionProgressStatus;
import nextstep.courses.domain.course.session.apply.ApplyRepository;
import nextstep.courses.fixture.ApplyFixtures;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.courses.service.SessionService;
import nextstep.payments.fixture.PaymentFixtures;
import nextstep.qna.NotFoundException;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private ApplyRepository applyRepository;

    @InjectMocks
    private SessionService sessionService;

    @Test
    @DisplayName("주어진 강의 정보로 강의를 생성한다.")
    void create_success() {
        Session savedSession = SessionFixtures.createdFreeSession();
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.create(1L, savedSession);
        Session findSession = sessionRepository.findById(1L).orElseThrow(NotFoundException::new);

        assertThat(findSession.getId()).isEqualTo(1L);
        assertThat(findSession.getSessionDetail()).isEqualTo(savedSession.getSessionDetail());
    }

    @Test
    @DisplayName("수강 신청은 수강 신청 인원에 해당 인원이 추가된다.")
    void apply_success() {
        Session savedSession = SessionFixtures.createdChargedSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.ONGOING);
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.applySession(NsUserFixtures.TEACHER_JAVAJIGI_1L,
                savedSession.getId(),
                PaymentFixtures.payment(),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(savedSession.applyCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("changeOnReady 는 강의 종료일보다 빠르면 강의를 준비중으로 변경 한다.")
    void changeOnReady_success() {
        Session savedSession = SessionFixtures.createdFreeSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.ONGOING);
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.changeOnReady(savedSession.getId(), SessionFixtures.DATE_2023_12_5);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionProgressStatus.READY);
    }

    @Test
    @DisplayName("changeOnGoing 는 강의 종료일보다 빠르면 강의를 진행중으로 변경 한다.")
    void changeOnGoing_success() {
        Session savedSession = SessionFixtures.createdFreeSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.READY);
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.changeOnGoing(savedSession.getId(), SessionFixtures.DATE_2023_12_6);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionProgressStatus.ONGOING);
    }

    @Test
    @DisplayName("changeOnEnd 는 강의 종료일보다 늦으면 강의를 종료로 변경 한다.")
    void changeOnEnd_success() {
        Session savedSession = SessionFixtures.createdFreeSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.READY);
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.changeOnEnd(savedSession.getId(), SessionFixtures.DATE_2023_12_12);

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionStatus()).isEqualTo(SessionProgressStatus.END);
    }

    @Test
    @DisplayName("approve 는 수강 신청을 승인 상태로 변경한다.")
    void approve_success() {
        Session savedSession = SessionFixtures.chargedSessionFullCanceled();
        when(applyRepository.findApplyByNsUserIdAndSessionId(1L, 1L))
                .thenReturn(Optional.of(ApplyFixtures.apply_one_canceled()));
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.approve(NsUserFixtures.TEACHER_JAVAJIGI_1L,
                savedSession.getId(),
                1L,
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionDetail().getSessionState().getApplies())
                .contains(ApplyFixtures.apply_one_approved());
    }

    @Test
    @DisplayName("cancel 는 수강 신청을 취소 상태로 변경한다.")
    void cancel_success() {
        Session savedSession = SessionFixtures.chargedSessionFullApproved();
        when(applyRepository.findApplyByNsUserIdAndSessionId(1L, 1L))
                .thenReturn(Optional.of(ApplyFixtures.apply_one_approved()));
        when(sessionRepository.findById(savedSession.getId())).thenReturn(Optional.of(savedSession));

        sessionService.cancel(NsUserFixtures.TEACHER_JAVAJIGI_1L,
                savedSession.getId(),
                1L,
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(savedSession.getId()).isEqualTo(1L);
        assertThat(savedSession.getSessionDetail().getSessionState().getApplies())
                .contains(ApplyFixtures.apply_one_canceled());
    }
}
