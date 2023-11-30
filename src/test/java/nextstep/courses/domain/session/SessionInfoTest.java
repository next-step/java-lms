package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionInfoTest {
    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("제목이 비어있으면, 예외가 발생한다.")
    void testTitleIsNotBlank(String title) {
        //when, then
        assertThatThrownBy(() -> new SessionInfo(title, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("title cannot be blank");
    }

    @Test
    @DisplayName("가격이 0원 미만이면, 예외가 발생한다.")
    void testTitleIsNull() {
        //given
        final long price = -1;

        //when, then
        assertThatThrownBy(() -> new SessionInfo("TDD 강의", price))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("price cannot be negative");
    }
}
