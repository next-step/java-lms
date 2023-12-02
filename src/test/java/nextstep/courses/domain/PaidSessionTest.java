package nextstep.courses.domain;

import nextstep.courses.exception.OverMaxStudentsException;
import nextstep.courses.exception.PaymentMismatchException;
import nextstep.payments.domain.PaymentTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 01);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 03);
    private static final PaidSession PAID_SESSION_10000 = new PaidSession(1L, CoverImageTest.IMAGE1, Status.NOT_OPEN, START_DATE, END_DATE, 1, 10_000L);

    @Test
    @DisplayName("유료 강의 수강 신청 시 최대 수강 인원을 초과하면 예외를 던진다.")
    void register_over_students() {
        Session paidSession = new PaidSession(1L, CoverImageTest.IMAGE1, Status.NOT_OPEN, START_DATE, END_DATE, 1, 10_000L);

        paidSession.register(PaymentTest.PAID_PAYMENT_10000);

        assertThatThrownBy(() -> paidSession.register(PaymentTest.PAID_PAYMENT_8000))
                .isInstanceOf(OverMaxStudentsException.class);
    }

    @Test
    @DisplayName("유료 강의 수강 신청 시 결제금액과 수강료가 일치하는지 확인하지 않으면 예외를 던진다.")
    void session_fee_test() {
        Session paidSession = PAID_SESSION_10000;

        assertThatThrownBy(() -> paidSession.register(PaymentTest.PAID_PAYMENT_8000))
                .isInstanceOf(PaymentMismatchException.class);
    }
}
