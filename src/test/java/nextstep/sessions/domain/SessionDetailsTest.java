package nextstep.sessions.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.END;
import static nextstep.sessions.domain.SessionStatus.RECRUITING;
import static nextstep.sessions.domain.SessionType.FREE;
import static nextstep.sessions.domain.SessionType.PAID;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionDetailsTest {

    @DisplayName("무료강의는 수강신청을 언제든 할 수 있다.(조건X)")
    @Test
    void always() {
        SessionDetails details = new SessionDetails(40, 0, 30000, FREE, RECRUITING);
        details.register(NsUserTest.JAVAJIGI, 30000L);
    }

    @DisplayName("유료강의는 수강신청을 했을 때, 최대 수강 인원을 초과하면 예외를 반환한다")
    @Test
    void greaterThanMax() {
        int currentCountOfStudents = 40;
        int maxOfStudents = 40;

        SessionDetails details = new SessionDetails(currentCountOfStudents, maxOfStudents, 30000, PAID, RECRUITING);

        assertThatThrownBy(() -> details.register(NsUserTest.JAVAJIGI, 30000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("이 강의의 현재 수강 신청 인원: (%s)명, 최대 수강 인원: (%s)명이므로 현재 마감이 된 상태입니다.", currentCountOfStudents, maxOfStudents));
    }

    @DisplayName("강의가 모집중이 아닐때 수강신청을 하면 예외를 반환한다")
    @Test
    void statusIsNotRecruiting() {
        SessionStatus end = END;
        SessionDetails details = new SessionDetails(39, 40, 30000, PAID, end);

        assertThatThrownBy(() -> details.register(NsUserTest.JAVAJIGI, 30000L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(String.format("현재 강의는 (%s)인 상태입니다.", end));
    }

    @DisplayName("강의의 가격과 결제한 금액이 같지 않은지 검증한다")
    @Test
    void isSameAmount() {
        SessionDetails details = new SessionDetails(40, 0, 30000, PAID, RECRUITING);

        assertThat(details.isNotSamePrice(20000)).isTrue();
    }
}
