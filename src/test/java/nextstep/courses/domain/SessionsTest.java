package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionsTest {
    @Test
    @DisplayName("동일한 강의를 등록 시, IllegalArgumentException 예외 발생")
    void add_duplicate_session_then_throw_IllegalArgumentException() {
        // given
        Sessions sessions = new Sessions();
        sessions.add(SessionTest.TDD_SESSION);

        // when
        assertThatThrownBy(() -> sessions.add(SessionTest.TDD_SESSION))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 강의입니다: " + SessionTest.TDD_SESSION.name());
    }
}