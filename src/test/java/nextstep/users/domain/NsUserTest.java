package nextstep.users.domain;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Session;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @DisplayName("SessionId 를 입력받아 결제 정보를 구한다.")
    @Test
    void test02() {
        int sessionId = 1;
        Payment payment = JAVAJIGI.pay(sessionId, 10_000);
        Payment result = JAVAJIGI.findPaymentBySessionId(sessionId);
        assertThat(result).isEqualTo(payment);
    }

    @DisplayName("특정 강의에 대한 결제 내역이 없으면 예외가 발생한다.")
    @Test
    void test03() {
        assertThatThrownBy(() -> JAVAJIGI.findPaymentBySessionId(1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의에 대한 결제 내역이 없습니다.");
    }
}
