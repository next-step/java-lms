package nextstep.enrollment.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class EnrollmentTest {
    @Test
    void 결제_금액_강의_금액_일치() {
        new Enrollment(new NsUser(), new Session(10_000L), new Payment("user_id", 0L, 0L, 10_000L));
    }

    @Test
    void 결제_금액_강의_금액_불일치() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                        new Enrollment(new NsUser(), new Session(10_000L), new Payment("user_id", 0L, 0L, 20_000L)))
                .withMessage("결제 금액이 수강료와 일치하지 않습니다.");
    }
}