package nextstep.courses.domain;

import static nextstep.courses.domain.SessionCoverImageTest.SAMPLE_COVER_IMAGE;
import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CanNotJoinSessionException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaidSessionTest {

    @Test
    @DisplayName("모집정원을 초과하면 강의 참여 시 예외가 발생한다")
    void join_fail_for_exceed_capacity() {
        // given
        PaidSession session = new PaidSession(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, SessionStatus.RECRUIT, 800_000L, 1,
            FIXED_DATE_TIME
        );
        NsUser oldLearner = NsUserTest.JAVAJIGI;
        session.join(oldLearner, new Payment(session.getId(), oldLearner.getId(), session.getPrice(), FIXED_DATE_TIME));

        // when, then
        NsUser newLearner = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> session.join(newLearner, new Payment(session.getId(),newLearner .getId(), session.getPrice(), FIXED_DATE_TIME)))
            .isInstanceOf(CanNotJoinSessionException.class);
    }

    @Test
    @DisplayName("유료 강의인데 Payment가 null이라면 예외가 발생한다")
    void join_fail_for_paid_session_but_payment_null() {
        // given
        PaidSession session = new PaidSession(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, SessionStatus.RECRUIT, 800_000L, 1,
            FIXED_DATE_TIME
        );

        // when, then
        assertThatThrownBy(() -> session.join(NsUserTest.JAVAJIGI, null))
            .isInstanceOf(CanNotJoinSessionException.class);
    }

    @Test
    @DisplayName("다른 강의에 대한 결제 정보라면 예외가 발생한다")
    void join_fail_for_another_session_payment() {
        // given
        PaidSession session = new PaidSession(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, SessionStatus.RECRUIT, 800_000L, 1,
            FIXED_DATE_TIME
        );
        NsUser learner = NsUserTest.JAVAJIGI;

        // when, then
        assertThatThrownBy(() -> session.join(learner, new Payment(100L, learner.getId(), session.getPrice(), FIXED_DATE_TIME)))
            .isInstanceOf(CanNotJoinSessionException.class);
    }

    @Test
    @DisplayName("다른 강의에 대한 결제 정보라면 예외가 발생한다")
    void join_fail_for_not_enough_payment() {
        // given
        PaidSession session = new PaidSession(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, SessionStatus.RECRUIT, 800_000L, 1,
            FIXED_DATE_TIME
        );
        NsUser learner = NsUserTest.JAVAJIGI;
        long paidAmount = session.getPrice() - 1;

        // when, then
        assertThatThrownBy(() -> session.join(learner, new Payment(session.getId(), learner.getId(),
            paidAmount, FIXED_DATE_TIME)))
            .isInstanceOf(CanNotJoinSessionException.class);
    }
}
