package nextstep.users.domain;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class NsUserTest {
    public static final NsUser JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final NsUser SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @DisplayName("특정 강의를 위한 수강료를 결제한다.")
    @Test
    void test01() {
        Session session = new FreeSession(1, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        Payment payment = JAVAJIGI.pay(session.getId(), 10_000);
        assertThat(payment.isSameAmount(10_000)).isTrue();
    }
}
