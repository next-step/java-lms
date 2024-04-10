package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.PAID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private List<NsUser> listeners;
    private Session tddCleanCodeJava;

    @BeforeEach
    void setUp() {
        SessionDetails details = new SessionDetails(40, 0, 30000, PAID, RECRUITING);
        listeners = new ArrayList<>();
        tddCleanCodeJava = new Session(1L, "tdd, 클린코드 java", details, listeners);
    }

    @DisplayName("강의의 가격과 결제 금액이 같을 때, 수강신청이 된다")
    @Test
    void register() {
        Payment payment = new Payment("tdd, 클린코드 java", 1L, 1L, 30000L);

        assertThat(listeners.size()).isEqualTo(0);
        tddCleanCodeJava.register(NsUserTest.JAVAJIGI, payment.getAmount());
        assertThat(listeners.size()).isEqualTo(1);
    }

    @DisplayName("강의의 가격과 결제 금액이 같지 않으면 예외를 반환한다")
    @Test
    void registerException() {
        Payment payment = new Payment("tdd, 클린코드 java", 1L, 1L, 20000L);

        assertThatThrownBy(() -> tddCleanCodeJava.register(NsUserTest.JAVAJIGI, payment.getAmount()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제한 금액이 강의의 가격과 일치하지 않습니다.");
    }

}
