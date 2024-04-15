package nextstep.session.domain;

import nextstep.exception.SessionStatusException;
import nextstep.session.type.SessionEnrollType;
import nextstep.session.type.SessionStatusType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStatusTest {

    @DisplayName("세션 유형이 준비중 + 비모집중이면, 진행중 + 비모집중으로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromReadyEnrollToProgressEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.PROGRESS);
        assertThat(sessionStatus.getSessionEnrollType())
                .isEqualTo(SessionEnrollType.NOT_ON_ENROLL);
    }

    @DisplayName("세션 유형이 준비중 + 모집중이면, 진행중 + 모집중으로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromReadyNotEnrollToProgressNotEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.changeEnroll();
        sessionStatus = sessionStatus.toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.PROGRESS);
        assertThat(sessionStatus.getSessionEnrollType())
                .isEqualTo(SessionEnrollType.ON_ENROLL);
    }

    @DisplayName("세션 유형이 진행중 + 비모집중이면, 종료 + 비모집중으로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromProgressNotEnrollToFinishNotEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus().toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.FINISHED);
        assertThat(sessionStatus.getSessionEnrollType())
                .isEqualTo(SessionEnrollType.NOT_ON_ENROLL);
    }

    @DisplayName("세션 유형이 진행중 + 모집중이면, 종료 + 비모집중으로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromProgressEnrollToFinishNotEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.changeEnroll();
        sessionStatus = sessionStatus.toNextStatus().toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.FINISHED);
        assertThat(sessionStatus.getSessionEnrollType())
                .isEqualTo(SessionEnrollType.NOT_ON_ENROLL);
    }

    @DisplayName("세션 유형을 FINISHED -> ON_ENROLL로 변경할 수 없다.")
    @Test
    void sessionStatusCannotChangeFromFinishedToOnEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus().toNextStatus();

        // then
        assertThatThrownBy(sessionStatus::toPreviousStatus)
                .isInstanceOf(SessionStatusException.class);
    }

    @DisplayName("READY에선 이전 세션으로 돌아갈 수없다.")
    @Test
    void cantChangeReadyToPreviouse() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // then
        assertThatThrownBy(sessionStatus::toPreviousStatus)
                .isInstanceOf(SessionStatusException.class);
    }

    @DisplayName("FINISHED에선 다음 세션으로 돌아갈 수없다. 시도 할 경우 SessionStatusException을 던진다.")
    @Test
    void cantChangeFinishedToNext() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus().toNextStatus();

        // then
        assertThatThrownBy(sessionStatus::toNextStatus)
                .isInstanceOf(SessionStatusException.class);
    }

    @DisplayName("세션이 준비중인지 확인 할 수 있다.")
    @Test
    void checkOnReady() {
        // given
        SessionStatus sessionStatus1 = SessionStatus.create();
        SessionStatus sessionStatus2 = SessionStatus.create();

        // when
        sessionStatus2 = sessionStatus2.toNextStatus();

        // then
        assertThat(sessionStatus1.onReady()).isTrue();
        assertThat(sessionStatus2.onReady()).isFalse();
    }

    @DisplayName("어느 상태든 모집중이면, 모집 가능상태가 된다.")
    @Test
    void canEnroll() {
        // given
        SessionStatus readyEnroll = SessionStatus.create();
        SessionStatus progressEnroll = SessionStatus.create();

        // when
        readyEnroll = readyEnroll.changeEnroll();
        progressEnroll = progressEnroll.toNextStatus().changeEnroll();

        // then
        assertThat(readyEnroll.canEnroll()).isTrue();
        assertThat(progressEnroll.canEnroll()).isTrue();
    }

    @DisplayName("어느 상태든 모집중이 아니면, 모집이 불가능하다.")
    @Test
    void cantEnroll() {
        // given
        SessionStatus readyEnroll = SessionStatus.create();
        SessionStatus progressEnroll = SessionStatus.create();
        SessionStatus finished = SessionStatus.create();

        // when
        progressEnroll = progressEnroll.toNextStatus();
        finished = finished.toNextStatus().toNextStatus();

        // then
        assertThat(readyEnroll.canEnroll()).isFalse();
        assertThat(progressEnroll.canEnroll()).isFalse();
        assertThat(finished.canEnroll()).isFalse();
    }
}
