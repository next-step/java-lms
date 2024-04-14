package nextstep.courses.domain;

import nextstep.payments.domain.PaymentTest;
import org.junit.jupiter.api.*;

import static nextstep.courses.domain.SessionTest.L1;
import static org.assertj.core.api.Assertions.*;

class PaySessionTest {

    public static PaySession PAY_SESSION1;

    @BeforeEach
    void init() {
        PAY_SESSION1 = new PaySession(L1);
    }

    @DisplayName("유료 강의 지불 여부 확인")
    @Test
    void 수강신청_정상기능() {
        PaySession session = new PaySession(new Session());
        session.join(JoinUserTest.JAVAJIGI, PaymentTest.PAID_PAYMENT);

        assertThat(session).isEqualTo(PAY_SESSION1);
    }

    @DisplayName("유료 강의 미지불 여부 확인")
    @Test
    void 수강신청_미지불() {
        PaySession session = new PaySession(new Session());

        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(() -> session.join(JoinUserTest.JAVAJIGI, PaymentTest.NOTPAID_PAYMENT))
            .withMessage("유료 강의의 지불이 완료되지 않았습니다.");
    }

}
