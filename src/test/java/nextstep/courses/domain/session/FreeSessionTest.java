package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.FreeSession.FREE_FEE;
import static nextstep.courses.domain.session.Session.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class FreeSessionTest {

    @Test
    @DisplayName("무료 수강 신청 가능")
    void testFreeSession_isOpened() {
        // given
        FreeSession freeSession = getFreeSession(true);
        NsUser user = new NsUser();
        Payment payment = getPayment(freeSession, user, FREE_FEE);

        // when
        freeSession.enrollStudent(user, payment);

        // then
        assertTrue(freeSession.isOpenForEnrollment());
    }

    @Test
    @DisplayName("무료 수강 신청 불가능 - 강의 상태가 모집중이 아님")
    void testFreeSession_isClosed_ShouldThrowException() {
        // given
        FreeSession freeSession = getFreeSession(false);
        NsUser user = new NsUser();
        Payment payment = getPayment(freeSession, user, FREE_FEE);

        // when, then
        assertFalse(freeSession.isOpenForEnrollment());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            freeSession.enrollStudent(user, payment);
        }).withMessageContaining(SESSION_NOT_OPENED);
    }

    @Test
    @DisplayName("무료 수강 신청 불가능 - 강의료와 결재금액이 매칭되지 않음")
    void testFreeSession_isNotPaymentAmountMatching_ShouldThrowException() {
        // given
        FreeSession freeSession = getFreeSession(true);
        NsUser user = new NsUser();
        Payment payment = getPayment(freeSession, user, 1L);

        // when, then
        assertTrue(freeSession.isOpenForEnrollment());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            freeSession.enrollStudent(user, payment);
        }).withMessageContaining(PAYMENT_NOT_MATCHING);
    }

    @Test
    @DisplayName("무료 수강 신청 불가능 - 유저가 이미 수강신청을 완료함")
    void testFreeSession_hasAlreadyEnrolled_ShouldThrowException() {
        // given
        FreeSession freeSession = getFreeSession(true);
        NsUser user = new NsUser();
        Payment payment = getPayment(freeSession, user, FREE_FEE);

        // when
        freeSession.enrollStudent(user, payment);

        // then
        assertTrue(freeSession.isOpenForEnrollment());
        assertAll(
                "User has enrolled the free session once correctly",
                () -> assertTrue(user.hasEnrolledSession(freeSession))
        );

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            freeSession.enrollStudent(user, payment);
        }).withMessageContaining(ENROLLMENT_ALREADY_DONE);
    }

    private FreeSession getFreeSession(boolean isOpenForEnrollment) {
        Long sessionId = 1L;
        SessionPeriod sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024,1,1,0,0,0),
                LocalDateTime.of(2024,4,1,0,0,0));
        List<CoverImage> coverImages = List.of(CoverImage.of(1L,"jpg", 1024, 300,200));
        return FreeSession.of(sessionId, sessionPeriod, coverImages, SessionStatusEnum.PENDING, 0, isOpenForEnrollment);
    }

    private Payment getPayment(Session session, NsUser user, Long fee) {
        return new Payment("SESSION_PAYMENT", session.getSessionId(), user.getId(), fee);
    }

}
