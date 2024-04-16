package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionStatusTest {

    @Test
    void check() {
        SessionStatus status = new SessionStatus("recruit");
        assertThatThrownBy(() -> {
            new SessionStatus("Wrong");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("잘못된 강의 상태 입니다");

        assertThat(status.check()).isEqualTo(true);
    }
}