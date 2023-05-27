package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    @Test
    @DisplayName("강의 상태가 모집중인 경우 수강신청이 가능하다")
    void status() {
        SessionStatus recruiting = SessionStatus.RECRUITING;
        assertThat(recruiting.canEnroll()).isTrue();
    }

    @ParameterizedTest
    @DisplayName("강의 상태가 준비중, 종료인 경우 신청할 수 없다")
    @EnumSource(names = {"PREPARING", "END"})
    void status2(SessionStatus sessionStatus) {
        assertThat(sessionStatus.canEnroll()).isFalse();
    }
}
