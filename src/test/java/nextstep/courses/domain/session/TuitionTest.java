package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TuitionTest {

    public static final Payment P1 = new Payment(1L, 2L, 30_000L);

    @Test
    void 강의금액과_결제금액_일치여부_확인() {
        assertThat(new Tuition(300_000L).matchAmount(P1)).isFalse();
    }
}
