package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

class SessionNameTest {
    @ParameterizedTest(name = "[{index}/2] {displayName}")
    @NullAndEmptySource
    @DisplayName("세션 이름이 null이거나 공백일 경우, IllegalArgumentException 예외 발생")
    void session_name_is_null_or_empty_then_throw_IllegalArgumentException(String invalidSessionName) {
        assertThatThrownBy(() -> SessionName.of(invalidSessionName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세션 이름은 null이나 공백일 수 없습니다: " + invalidSessionName);
    }

    @Test
    void session_name_caching() {
        String name = "nextstep_tdd";
        Assertions.assertThat(SessionName.of(name)).isSameAs(SessionName.of(name));
    }
}