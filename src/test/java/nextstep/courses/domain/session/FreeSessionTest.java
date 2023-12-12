package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class FreeSessionTest {

    @Test
    public void 무료강의_수강신청() {
        Session session = new FreeSession();
        Assertions.assertThatCode(() -> {
            session.enroll(new Payment(NsUserTest.JAVAJIGI.getId()));
            session.enroll(new Payment(NsUserTest.SANJIGI.getId()));
        }).doesNotThrowAnyException();
    }
}
