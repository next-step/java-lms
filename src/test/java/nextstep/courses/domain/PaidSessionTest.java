package nextstep.courses.domain;

import nextstep.courses.CannotSignUpException;
import nextstep.payments.domain.Payment;
import nextstep.payments.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaidSessionTest {

    private PaidSession paidSession = PaidSession.feeOf("step4", 1, 10_000L);
    private Payment payment = Payment.paidOf("1A");

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다. ")
    void sessionStudentTest() throws CannotSignUpException {
        paidSession.signUp(payment);
        assertThrows(CannotSignUpException.class, () -> paidSession.signUp(payment));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void payCheckTest() throws CannotSignUpException {
        PaidSession paidSession = PaidSession.feeOf("step4", 2, 10_000L);

        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.paymentPaid("1");

        assertDoesNotThrow(() -> paidSession.signUp(payment));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않는 경우 Exception Throw")
    void payCheckExceptionTest() throws CannotSignUpException {
        PaidSession paidSession = PaidSession.feeOf("step4", 2, 5_000L);

        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.paymentPaid("1");

        assertThrows(CannotSignUpException.class, () -> paidSession.signUp(payment));
    }

}
