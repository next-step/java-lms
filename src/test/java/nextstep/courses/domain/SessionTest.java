package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Money;
import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionTestBuilder.aSession;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("수강신청 - 성공")
    void enroll_success() {
        Long userId = 100L;
        Session session = aSession()
                .build();

        Payment payment = PaymentTestBuilder.validPaymentFor(session, userId);

        assertThatCode(() -> session.enroll(userId, payment))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수강신청 - 실패(강의 종료)")
    void enroll_fail_closed() {
        Long userId = 100L;
        Session session = aSession()
                .withClosedSession()
                .build();

        Payment payment = PaymentTestBuilder.validPaymentFor(session, userId);

        assertThatThrownBy(() -> session.enroll(userId, payment))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("수강신청 - 실패(결제 금액 불일치)")
    void enroll_fail_invalidPayment() {
        Long userId = 1L;
        Session session = aSession()
                .withPaidEnrollment(new Money(5000))
                .build();

        Payment payment = PaymentTestBuilder.inValidPaymentFor(session, userId);

        assertThatThrownBy(() -> session.enroll(userId, payment))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강신청 - 실패(정원 초과)")
    void enroll_fail_enrollments() {
        Long userId = 300L;
        Session session = aSession()
                .withCapacity(10)
                .withFullEnrollments(10)
                .build();

        Payment payment = PaymentTestBuilder.validPaymentFor(session, userId);

        assertThatThrownBy(() -> session.enroll(userId, payment))
                .isInstanceOf(IllegalStateException.class);
    }
}