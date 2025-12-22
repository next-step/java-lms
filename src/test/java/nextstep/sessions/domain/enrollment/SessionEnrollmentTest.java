package nextstep.sessions.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionStatus;
import org.junit.jupiter.api.Test;

class SessionEnrollmentTest {


    @Test
    void sessionStatusIsPreparingOnCreation() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.freeUnlimited();
        assertThat(sessionEnrollment.status()).isEqualTo(SessionStatus.PREPARING);
    }

    @Test
    void whenStartRecruiting_StatusIsOpen() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.freeUnlimited();
        sessionEnrollment.startRecruiting();
        assertThat(sessionEnrollment.status()).isEqualTo(SessionStatus.OPEN);
    }

    @Test
    void whenSessionStatusIsNotOpen_thenThrows() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.freeUnlimited();
        assertThatThrownBy(() -> sessionEnrollment.enroll(EnrollmentTest.E1))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(Session.ERROR_SESSION_NOT_OPEN);
    }

    @Test
    void whenCapacityFull_thenThrows() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.paidLimited(1_000, 1);
        sessionEnrollment.startRecruiting();
        sessionEnrollment.enroll(EnrollmentTest.E1);
        assertThatThrownBy(() -> sessionEnrollment.enroll(EnrollmentTest.E2))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining(Session.ERROR_CAPACITY_EXCEEDED);
    }

    @Test
    void whenPaymentDifferent_thenThrows() {
        SessionEnrollment sessionEnrollment = SessionEnrollment.paidLimited(10_000, 1);
        sessionEnrollment.startRecruiting();
        assertThatThrownBy(() -> sessionEnrollment.enroll(EnrollmentTest.E1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Session.ERROR_PAYMENT_AMOUNT_MISMATCH);
    }

}