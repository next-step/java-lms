package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SessionTest {

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"Preparing", "Closed"})
    void 모집중_제외_register_불가(SessionStatus status) {
        Session session = new Session(1L, LocalDateTime.MIN, LocalDateTime.MAX, null, true, status,
            30);

        assertThatThrownBy(() -> session.register(userA(), LocalDateTime.now()))
            .isInstanceOf(IllegalStateException.class)
            .hasMessage("모집중이 아닙니다.");
    }

    @Test
    void 강의_신청_기간_예외() {
        Session session = new Session(1L, LocalDateTime.of(2022, 10, 19, 0, 0), LocalDateTime.MAX, null, true, SessionStatus.Recruiting,
            30);

        assertThatThrownBy(() -> session.register(userA(), LocalDateTime.of(2022, 10, 18, 0, 0)))
            .isInstanceOf(RuntimeException.class)
            .hasMessage("강의 신청 기간이 아닙니다.");
    }

    private static NsUser userA() {
        return new NsUser(1L, "A", "A", "A", "A@A.A");
    }

}
