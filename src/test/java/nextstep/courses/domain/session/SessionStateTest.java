package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionStateTest {

    @Test
    @DisplayName("준비중 상태에는 등록할 수 있다.")
    void canApply() {
        SessionState state = SessionState.RECRUITING;
        assertTrue(state.canRecruit());
    }

    @ParameterizedTest
    @EnumSource(value = SessionState.class, mode = EnumSource.Mode.EXCLUDE, names = {"RECRUITING"})
    @DisplayName("준비중이 아닌 상태에서는 등록할 수 없다.")
    void cannotApply(SessionState state) {
        assertFalse(state.canRecruit());
    }

}