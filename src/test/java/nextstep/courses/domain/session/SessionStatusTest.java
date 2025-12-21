package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class SessionStatusTest {

    @Test
    void 모집_중일_때는_true를_반환한다() {
        assertThat(SessionStatus.RECRUITING.canEnroll()).isTrue();
    }

    @ParameterizedTest(name = "상태가 {0}일 때는 false를 반환한다")
    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "END"})
    void 모집_상태가_아닐_때는_false를_반환한다(SessionStatus sessionStatus) {
        assertThat(sessionStatus.canEnroll()).isFalse();
    }
}
