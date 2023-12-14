package nextstep.courses.domain.participant;

import nextstep.courses.exception.AlreadyAcceptedUserException;
import nextstep.courses.exception.AlreadyRejectedUserException;
import nextstep.courses.type.ParticipantSelectionStatus;
import nextstep.qna.UnAuthorizedException;
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
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING);
        // when
        // then
        assertEquals(ParticipantSelectionStatus.WAITING, sessionUserEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 참여자를 선발한다.")
    @Test
    void 강의_참여자를_선발한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING);
        // when
        SessionUserEnrolment acceptEnrolment = sessionUserEnrolment.accept();
        // then
        assertEquals(ParticipantSelectionStatus.ACCEPT, acceptEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 참여자를 선발탈락시킨다.")
    @Test
    void 강의_참여자를_선발탈락시킨다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING);
        // when
        SessionUserEnrolment rejectEnrolment = sessionUserEnrolment.reject();
        // then
        assertEquals(ParticipantSelectionStatus.REJECT, rejectEnrolment.subscriptionStatus());
    }

    @DisplayName("이미 선발 상태인 참여자를 선발하면 예외가 발생한다.")
    @Test
    void 이미_선발_상태인_참여자를_선발하면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.ACCEPT);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.accept())
                .isInstanceOf(AlreadyAcceptedUserException.class);
    }

    @DisplayName("이미 선발탈락 상태인 참여자를 선발탈락시키면 예외가 발생한다.")
    @Test
    void 이미_선발탈락_상태인_참여자를_선발탈락시키면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.REJECT);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.reject())
                .isInstanceOf(AlreadyRejectedUserException.class);
    }

    @DisplayName("강의 참여자를 승인한다.")
    @Test
    void 강의_참여자를_승인한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.ACCEPT);
        // when
        SessionUserEnrolment approveEnrolment = sessionUserEnrolment.approve();
        // then
        assertEquals(ParticipantSelectionStatus.ACCEPT, approveEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 탈락자를 취소시킨다.")
    @Test
    void 강의_탈락자를_취소시킨다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.REJECT);
        // when
        SessionUserEnrolment cancelEnrolment = sessionUserEnrolment.cancel();
        // then
        assertEquals(ParticipantSelectionStatus.REJECT, cancelEnrolment.subscriptionStatus());
    }

    @DisplayName("강의 참여자를 선발하지않으면 승인에 대해 예외를 발생한다.")
    @Test
    void 강의_참여자를_승인하지_않은_상태에서_승인취소하면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.WAITING);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.approve())
                .isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("강의 참여자를 선발탈락 시키지 않은 상태에서 승인취소하면 예외가 발생한다.")
    @Test
    void 강의_참여자를_선발탈락_시키지_않은_상태에서_승인취소하면_예외가_발생한다() {
        // given
        sessionUserEnrolment = new SessionUserEnrolment(1L, 1L, ParticipantSelectionStatus.ACCEPT);
        // when
        // then
        assertThatThrownBy(() -> sessionUserEnrolment.cancel())
                .isInstanceOf(UnAuthorizedException.class);
    }
}