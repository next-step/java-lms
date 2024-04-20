package nextstep.sessions.domain;

import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.builder.SessionBuilder;
import nextstep.sessions.domain.builder.SessionRegisterDetailsBuilder;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Session tddCleanCodeJava;

    @BeforeEach
    void setUp() {
        tddCleanCodeJava = new SessionBuilder()
                .withSessionName("TDD, CleanCode")
                .withSessionRegisterDetails(new SessionRegisterDetailsBuilder().withPrice(new Price(30000L)).build())
                .build();
    }

    @DisplayName("강의의 가격과 결제 금액이 같을 때, 수강신청이 된다")
    @Test
    void register() {
        Payment payment = new Payment("javajigi", 1L, 1L, 30000L);

        tddCleanCodeJava.register(NsUserTest.JAVAJIGI, payment);

        assertThat(tddCleanCodeJava.isContainListener(NsUserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("강의의 가격과 결제 금액이 같지 않으면 예외를 반환한다")
    @Test
    void registerException() {
        Payment payment = new Payment("javajigi", 1L, 1L, 10000L);

        assertThatThrownBy(() -> tddCleanCodeJava.register(NsUserTest.JAVAJIGI, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제한 금액이 강의의 가격과 일치하지 않습니다.");
    }

}
