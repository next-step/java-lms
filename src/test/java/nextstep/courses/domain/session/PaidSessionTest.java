package nextstep.courses.domain.session;

import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.courses.PriceMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {

    @Test
    public void 최대_수강인원_초과() {
        Session session = new PaidSession(1);
        assertThatThrownBy(() -> {
            session.enroll(new Payment(NsUserTest.JAVAJIGI.getId()));
            session.enroll(new Payment(NsUserTest.SANJIGI.getId()));
        }).isInstanceOf(MaxStudentsNumberExceededException.class);
    }

    @Test
    public void 결제금액_수강료_불일치() {
        Session session = new PaidSession(1, 10_000);
        Payment payment = new Payment("p1", 1L, 1L, 5_000L);
        assertThatThrownBy(() -> {
            session.enroll(payment);
        }).isInstanceOf(PriceMismatchException.class);
    }
}
