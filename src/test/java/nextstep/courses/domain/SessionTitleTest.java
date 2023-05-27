package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.*;

class SessionTitleTest {
    @ParameterizedTest(name = "[{index}/2] {displayName}")
    @NullAndEmptySource
    @DisplayName("세션 타이틀이 null이거나 공백일 경우, IllegalArgumentException 예외 발생")
    void session_title_is_null_or_empty_then_throw_IllegalArgumentException(String invalidSessionTitle) {
        assertThatThrownBy(() -> SessionTitle.of(invalidSessionTitle))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("세션 타이틀은 null이나 공백일 수 없습니다: " + invalidSessionTitle);
    }

    @Test
    void session_title_caching() {
        String title = "nextstep_tdd";
        Assertions.assertThat(SessionTitle.of(title)).isSameAs(SessionTitle.of(title));
    }
}