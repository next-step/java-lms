package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.CoverImage;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.domain.session.PaidSession.OVER_MAX_ENROLLMENTS;
import static nextstep.courses.domain.session.Session.*;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class PaidSessionTest {

    @Test
    @DisplayName("유료 수강 신청 가능")
    void testPaidSession_isOpened() {
        // given
        PaidSession paidSession = getPaidSession(true, 1, 0);
        NsUser user = new NsUser();
        Payment payment = getPayment(paidSession, user, paidSession.getFee());

        // when
        paidSession.enrollStudent(user, payment);

        // then
        assertTrue(paidSession.isOpenForEnrollment());
    }

    @Test
    @DisplayName("유료 수강 신청 불가능 - 강의 상태가 모집중이 아님")
    void testPaidSession_isClosed_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(false, 1, 0);
        NsUser user = new NsUser();
        Payment payment = getPayment(paidSession, user, paidSession.getFee());

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user, payment);
        }).withMessageContaining(SESSION_NOT_OPENED);
    }

    @Test
    @DisplayName("유료 수강 신청 불가능- 신청 가능 인원 초과")
    void testPaidSession_isEnrollmentFull_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(true, 1, 1);
        NsUser user = new NsUser();
        Payment payment = getPayment(paidSession, user, paidSession.getFee());

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user, payment);
        }).withMessageContaining(OVER_MAX_ENROLLMENTS);
    }

    @Test
    @DisplayName("유료 수강 신청 불가능 - 강의료와 결재금액이 매칭되지 않음")
    void testPaidSession_isNotPaymentAmountMatching_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(true, 1, 0);
        NsUser user = new NsUser();
        Payment payment = getPayment(paidSession, user, 1L);

        // when, then
        assertTrue(paidSession.isOpenForEnrollment());
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user, payment);
        }).withMessageContaining(PAYMENT_NOT_MATCHING);
    }

    @Test
    @DisplayName("유료 수강 신청 불가능 - 유저가 이미 수강신청을 완료함")
    void testPaidSession_hasAlreadyEnrolled_ShouldThrowException() {
        // given
        PaidSession paidSession = getPaidSession(true, 2, 1);
        NsUser user = new NsUser();
        Payment payment = getPayment(paidSession, user, paidSession.getFee());

        // when
        paidSession.enrollStudent(user, payment);

        // then
        assertTrue(paidSession.isOpenForEnrollment());
        assertAll(
                "User has enrolled the paid session once correctly",
                () -> assertTrue(user.hasEnrolledSession(paidSession))
        );

        // when, then
        assertThatIllegalArgumentException().isThrownBy(() -> {
            paidSession.enrollStudent(user, payment);
        }).withMessageContaining(ENROLLMENT_ALREADY_DONE);
    }

    private PaidSession getPaidSession(boolean isOpenForEnrollment, int maxEnrollments, int numberOfStudents) {
        Long sessionId = 1L;
        Long fee = 10000L;
        SessionPeriod sessionPeriod = SessionPeriod.of(
                LocalDateTime.of(2024,1,1,0,0,0),
                LocalDateTime.of(2024,4,1,0,0,0));
        List<CoverImage> coverImages = List.of(CoverImage.of(1L,"jpg", 1024, 300,200));

        return PaidSession.of(sessionId, sessionPeriod, coverImages,
                SessionStatusEnum.PENDING, isOpenForEnrollment, numberOfStudents,
                maxEnrollments, fee);
    }

    private Payment getPayment(Session session, NsUser user, Long fee) {
        return new Payment("SESSION_PAYMENT", session.getSessionId(), user.getId(), fee);
    }

}
