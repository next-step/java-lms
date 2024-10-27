package nextstep.courses.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.PaidSession.MAX_STUDENT_CAPACITY_MESSAGE;
import static nextstep.courses.domain.PaidSession.PAYMENT_MISMATCH_MESSAGE;
import static org.assertj.core.api.Assertions.*;

public class PaidSessionTest {

    private int maxRegisterCount;
    private long sessionAmount;
    private Payment payment1;
    private Payment payment2;
    private Payment payment3;
    private Payment paymentNotMatched;

    @BeforeEach
    void setUp() {
        maxRegisterCount = 30;
        sessionAmount = 10000L;

        payment1 = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, sessionAmount);
        payment2 = new Payment("pay2", 1L, NsUserTest.SANJIGI, sessionAmount);
        payment3 = new Payment("pay3", 1L, NsUserTest.THIRDJIGI, sessionAmount);
        paymentNotMatched = new Payment("pay4", 1L, NsUserTest.JAVAJIGI, 9999L);
    }

    @Test
    void create() {
        assertThatNoException().isThrownBy(() -> {
            PaidSession paidSession = new PaidSession(1L, maxRegisterCount, sessionAmount, NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
        });
    }

    @Test
    void register_성공() {
        PaidSession paidSession = new PaidSession(1L, maxRegisterCount, sessionAmount);
        paidSession.register(payment1);

        PaidSession expected = new PaidSession(1L, maxRegisterCount, sessionAmount, NsUserTest.JAVAJIGI);
        assertThat(paidSession).isEqualTo(expected);
    }

    @Test
    void register_강의_최대_수강인원_초과() {
        maxRegisterCount = 2;
        PaidSession paidSession = new PaidSession(1L, maxRegisterCount, sessionAmount);

        paidSession.register(payment1);
        paidSession.register(payment2);
        assertThatThrownBy(() -> {
            paidSession.register(payment3);
        }).isInstanceOf(MaxStudentCapacityException.class)
                .hasMessage(MAX_STUDENT_CAPACITY_MESSAGE);
    }

    @Test
    void register_결제한_금액과_수강료_불일치() {
        assertThatThrownBy(() -> {
            PaidSession paidSession = new PaidSession(1L, 2, sessionAmount);
            paidSession.register(paymentNotMatched);
        }).isInstanceOf(PaymentMismatchException.class)
                .hasMessage(PAYMENT_MISMATCH_MESSAGE);
    }
}
