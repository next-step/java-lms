package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void sessionStatusIsPreparingOnCreation() {
        Session session = new SessionTestBuilder().free().build();
        assertThat(session.status()).isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    void whenStartRecruiting_StatusIsOpen() {
        Session session = new SessionTestBuilder().free().build();
        session.startRecruiting();
        assertThat(session.status()).isEqualTo(SessionStatus.OPEN);
    }

    @Test
    void whenSessionStatusIsNotOpen_thenThrows() {
        Session session = new SessionTestBuilder().free().build();
        assertThatThrownBy(() -> session.enroll(EnrollmentTest.E1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(Session.ERROR_SESSION_NOT_OPEN);
    }

    @Test
    void whenCapacityFull_thenThrows() {
        Session session = new SessionTestBuilder().paid(1, 1_000).buildPaidSessionFull();
        assertThatThrownBy(() -> session.enroll(EnrollmentTest.E2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(Session.ERROR_CAPACITY_EXCEEDED);
    }

    @Test
    void whenPaymentDifferent_thenThrows() {
        Session session = new SessionTestBuilder().paid(5, 100_000).id(1L).build();
        session.startRecruiting();
        assertThatThrownBy(() -> session.enroll(EnrollmentTest.E1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Session.ERROR_PAYMENT_AMOUNT_MISMATCH);
    }

}
