package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("수강신청 - 성공")
    void enroll_success() {
        Long sessionId = 1L;
        Long userId = 100L;
        SessionState sessionState = SessionState.OPEN;
        Money price = new Money(5000);
        Capacity capacity = new Capacity(30, 20);
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(price, capacity);

        Payment payment = new Payment("p1", sessionId, userId, 5000L);

        Session session = new Session(sessionId, LocalDateTime.now(), LocalDateTime.now().plusDays(7)
                , 500_000, "test.jpg", 300, 200, policy, sessionState);

        assertThatCode(() -> session.enroll(userId, payment))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("수강신청 - 실패(상태:종료)")
    void enroll_fail_closed() {
        Long sessionId = 1L;
        Long userId = 100L;
        SessionState sessionState = SessionState.CLOSED;
        Money price = new Money(5000);
        Capacity capacity = new Capacity(30, 20);
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(price, capacity);

        Payment payment = new Payment("p1", sessionId, userId, 5000L);

        Session session = new Session(sessionId, LocalDateTime.now(), LocalDateTime.now().plusDays(7)
                , 500_000, "test.jpg", 300, 200, policy, sessionState);

        assertThatThrownBy(() -> session.enroll(userId, payment))
                .isInstanceOf(IllegalStateException.class);
    }
}