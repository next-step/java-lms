package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.PaidSession.OVER_MAX_ENROLLMENTS;
import static nextstep.courses.domain.session.PaidSession.PAYMENT_IS_NOT_MATCHING;
import static nextstep.courses.domain.session.Session.SESSION_NOT_OPENED;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaidSessionTest {

    @Test
    @DisplayName("유료 수강 신청 가능")
    void testPaidSession_isOpened() {
        // given
        PaidSession paidSession = getPaidSession(SessionStatusEnum.OPEN, 1);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 1L, 1L, 10000L));

        // when
        paidSession.enrollStudent(user);

        // then
        assertTrue(paidSession.isSessionOpened());
        assertEquals(paidSession.users.getNumberOfUsers(), 1);
    }

    @ParameterizedTest(name = "유료 수강 신청 불가능 - 강의 상태가 모집중이 아님")
    @ValueSource(strings = { "PENDING", "CLOSED" })
    void testPaidSession_isClosed_ShouldThrowException(SessionStatusEnum sessionStatus) {
        // given
        PaidSession paidSession = getPaidSession(sessionStatus, 1);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 1L, 1L, 10000L));

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user);
        }).withMessageContaining(SESSION_NOT_OPENED);
    }

    @Test
    @DisplayName("유료 수강 객체 생성 실패 - 신청 가능 인원 초과")
    void testPaidSession_isEnrollmentFull_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(SessionStatusEnum.OPEN, 0);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 1L, 1L, 10000L));

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user);
        }).withMessageContaining(OVER_MAX_ENROLLMENTS);
    }

    @Test
    @DisplayName("유료 수강 객체 생성 실패 - 유저가 지불한 수강신청 금액이 매칭되지 않음")
    void testPaidSession_isNotPaymentAmountMatching_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(SessionStatusEnum.OPEN, 1);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 1L, 1L, 9999L));

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user);
        }).withMessageContaining(PAYMENT_IS_NOT_MATCHING);
    }

    @Test
    @DisplayName("유료 수강 객체 생성 실패 - 유저가 신청한 수강신청 ID가 일치하지 않음")
    void testPaidSession_isNotPaymentSessionIdMatching_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(SessionStatusEnum.OPEN, 1);
        NsUser user = new NsUser();
        user.addPayment(new Payment("TEST", 9999L, 1L, 10000L));

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user);
        }).withMessageContaining(PAYMENT_IS_NOT_MATCHING);
    }

    private PaidSession getPaidSession(SessionStatusEnum sessionStatusEnum, int maxEnrollments) {
        Long sessionId = 1L;
        Long fee = 10000L;
        SessionPeriod sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024,1,1,0,0,0),
                LocalDateTime.of(2024,4,1,0,0,0));
        CoverImage coverImage = CoverImage.of("jpg", 1024, 300,200);
        SessionStatusEnum sessionStatus = sessionStatusEnum;

        return new PaidSession(sessionId, sessionPeriod, coverImage,
                sessionStatus, maxEnrollments, fee);
    }

}
