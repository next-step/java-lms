package nextstep.payments.domain;

import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {

    public static final Payment P1 = new Payment(1L, 2L, 30_000L);

    @Test
    void 결제_정상_생성() {
        assertThat(P1.getSessionId()).isEqualTo(1L);
        assertThat(P1.getNsUserId()).isEqualTo(2L);
        assertThat(P1.getAmount()).isEqualTo(30_000L);
    }

    @Test
    void 강의아이디_null_예외발생() {
        assertThatThrownBy(() -> new Payment(null, 1L, 30_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제한 강의 아이디는 필수입니다.");
    }

    @Test
    void 사용자아이디_null_예외발생() {
        assertThatThrownBy(() -> new Payment(1L, null, 30_000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사용자 아이디는 필수입니다.");
    }

    @Test
    void 결제금액_null_예외발생() {
        assertThatThrownBy(() -> new Payment(1L, 2L, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액은 필수입니다.");
    }

}
