package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.EnrollmentPolicy;
import nextstep.courses.domain.session.metadata.Period;
import nextstep.payments.domain.Payment;

public class EnrollmentPolicyTest {
    Period period;
    Payment payment;
    Session paidSession;

    @BeforeEach
    public void setUp() {
        period = new Period(LocalDate.now(), LocalDate.now().plusDays(1));
        EnrollmentPolicy paidPolicy = EnrollmentPolicy.paid(10_000, 10);
        paidSession = new Session(1L, period, SessionStatus.OPEN, paidPolicy);
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void remainingSeatOfFreeSession() {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.free();
        assertThat(enrollmentPolicy.remainingSeats(0)).isEmpty();
        assertThat(enrollmentPolicy.remainingSeats(1)).isEmpty();
        assertThat(enrollmentPolicy.hasCapacity(10)).isTrue();
    }

    @Test
    @DisplayName("유료 강의는 강의 수강 인원을 초과할 수 없다.")
    void overflowedSeatOfPaidSession() {
        EnrollmentPolicy enrollmentPolicy = EnrollmentPolicy.paid(10_000, 2);
        long enrolledCount = 2l;
        assertFalse(enrollmentPolicy.hasCapacity(enrolledCount));
        assertThatThrownBy(() -> enrollmentPolicy.validateEnrollment(enrolledCount))
            .isInstanceOf(CannotEnrollException.class);
    }

    @Test
    @DisplayName("수강료와 동일한 금액을 결제 시 등록된다.")
    void validPayForSession() {
        payment = new Payment("1L", 1l, 1l, 10_000L );
        assertDoesNotThrow(
            () -> paidSession.enroll(payment)
        );
        assertEquals(payment.pay(), paidSession.price());
    }

    @Test
    @DisplayName("수강료와 다른 금액 납부 시 결제되면 안된다.")
    void invalidPayForSession() {
        payment = new Payment("1L", 1l, 1l, 11_000L );
        assertThatThrownBy(
            () -> paidSession.enroll(payment)
        ).isInstanceOf(CannotEnrollException.class);
    }
}
