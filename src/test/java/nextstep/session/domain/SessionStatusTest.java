package nextstep.session.domain;

import nextstep.exception.SessionStatusException;
import nextstep.session.type.SessionStatusType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStatusTest {

    @DisplayName("세션 유형을 READY -> ON_ENROLL로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromReadyToOnEnroll() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.ON_ENROLL);
    }

    @DisplayName("세션 유형을 ON_ENROLL -> READY로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromOnEnrollToReady() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus().toPreviousStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.READY);
    }

    @DisplayName("세션 유형을 ON_ENROLL -> FINISHED로 변경할 수 있다.")
    @Test
    void sessionStatusChangeFromOnEnrollToFinished() {
        // given
        SessionStatus sessionStatus = SessionStatus.create();

        // when
        sessionStatus = sessionStatus.toNextStatus().toNextStatus();

        // then
        assertThat(sessionStatus.getSessionStatus())
                .isEqualTo(SessionStatusType.FINISHED);
    }

    @DisplayName("세션 유형을 FINISHED -> ON_ENROLL로 변경할 수 없다. 일치하지 않을 경우 SessionStatusException을 던진다.")
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

    @DisplayName("READY에선 이전 세션으로 돌아갈 수없다. 시도 할 경우 SessionStatusException을 던진다.")
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
}
