package nextstep.courses.domain.participant;

import nextstep.courses.exception.AlreadyAcceptedUserException;
import nextstep.courses.exception.AlreadyRejectedUserException;
import nextstep.courses.type.SessionSubscriptionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionUserEnrolmentTest {

    private SessionUserEnrolment sessionUserEnrolment;

    @DisplayName("강의 참여자 생성한다.")
    @Test
    void 강의_참여자_생성한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING);
        // when
        // then
        assertEquals(SessionSubscriptionStatus.WAITING, sessionUserEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 참여자를 수락한다.")
    @Test
    void 강의_참여자를_수락한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING);
        // when
        SessionUserEnrolment acceptEnrolment = sessionUserEnrolment.accept();
        // then
        assertEquals(SessionSubscriptionStatus.ACCEPT, acceptEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 참여자를 거절한다.")
    @Test
    void 강의_참여자를_거절한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.WAITING);
        // when
        SessionUserEnrolment rejectEnrolment = sessionUserEnrolment.reject();
        // then
        assertEquals(SessionSubscriptionStatus.REJECT, rejectEnrolment.subscriptionStatus());
    }

    @DisplayName("이미 수락 상태인 참여자를 수락하면 예외가 발생한다.")
    @Test
    void 이미_수락_상태인_참여자를_수락하면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.ACCEPT);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.accept())
                .isInstanceOf(AlreadyAcceptedUserException.class);
    }

    @DisplayName("이미 거절 상태인 참여자를 거절하면 예외가 발생한다.")
    @Test
    void 이미_거절_상태인_참여자를_거절하면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, SessionSubscriptionStatus.REJECT);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.reject())
                .isInstanceOf(AlreadyRejectedUserException.class);
    }
}