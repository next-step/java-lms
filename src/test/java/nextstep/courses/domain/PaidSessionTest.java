package nextstep.courses.domain;

import nextstep.courses.MaxStudentCapacityException;
import nextstep.courses.domain.vo.session.CoverImage;
import nextstep.courses.domain.vo.session.DateRange;
import nextstep.courses.domain.vo.session.Status;
import nextstep.payments.PaymentMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.PaidSession.MAX_STUDENT_CAPACITY_MESSAGE;
import static nextstep.courses.domain.PaidSession.PAYMENT_MISMATCH_MESSAGE;
import static nextstep.courses.domain.vo.session.CoverImageTest.*;
import static nextstep.courses.domain.vo.session.DateRangeTest.END;
import static nextstep.courses.domain.vo.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    public static final int MAX_REGISTER_COUNT = 30;
    public static final long SESSION_AMOUNT = 10000L;
    private PaidSession paidSession;
    private PaidSession exceedMaxRegisterCountPaidSession;
    private Payment payment1;
    private Payment payment2;
    private Payment payment3;
    private Payment paymentNotMatched;

    @BeforeEach
    void setUp() {
        paidSession = new PaidSession(1L,
                1L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT);

        exceedMaxRegisterCountPaidSession = new PaidSession(1L,
                1L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                2,
                SESSION_AMOUNT);

        payment1 = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, SESSION_AMOUNT);
        payment2 = new Payment("pay2", 1L, NsUserTest.SANJIGI, SESSION_AMOUNT);
        payment3 = new Payment("pay3", 1L, NsUserTest.THIRDJIGI, SESSION_AMOUNT);
        paymentNotMatched = new Payment("pay4", 1L, NsUserTest.JAVAJIGI, 9999L);
    }

    @Test
    void register_성공() {
        PaidSession actual = new PaidSession(1L,
                1L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT);

        paidSession.register(payment1);
        assertThat(actual).isNotEqualTo(paidSession);

        actual.register(payment1);
        assertThat(actual).isEqualTo(paidSession);
    }

    @Test
    void register_강의_최대_수강인원_초과() {
        assertThatThrownBy(() -> {
            exceedMaxRegisterCountPaidSession.register(payment1);
            exceedMaxRegisterCountPaidSession.register(payment2);
            exceedMaxRegisterCountPaidSession.register(payment3);
        }).isInstanceOf(MaxStudentCapacityException.class)
                .hasMessage(MAX_STUDENT_CAPACITY_MESSAGE);
    }

    @Test
    void register_결제한_금액과_수강료_불일치() {
        assertThatThrownBy(() -> {
            paidSession.register(paymentNotMatched);
        }).isInstanceOf(PaymentMismatchException.class)
                .hasMessage(PAYMENT_MISMATCH_MESSAGE);
    }
}
