package nextstep.sessions.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.PAID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private Session tddCleanCodeJava;

    @BeforeEach
    void setUp() {
        SessionDetails details = new SessionDetails(40, 50, 30000L, PAID, RECRUITING);
        tddCleanCodeJava = new Session(1L, "tdd, 클린코드 java", details);
    }

    @DisplayName("강의의 가격과 결제 금액이 같을 때, 수강신청이 된다")
    @Test
    void register() {
        tddCleanCodeJava.register(NsUserTest.JAVAJIGI, 30000L);
        assertThat(tddCleanCodeJava.isContainListener(NsUserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("강의의 가격과 결제 금액이 같지 않으면 예외를 반환한다")
    @Test
    void registerException() {
        assertThatThrownBy(() -> tddCleanCodeJava.register(NsUserTest.JAVAJIGI, 10000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제한 금액이 강의의 가격과 일치하지 않습니다.");
    }

}
