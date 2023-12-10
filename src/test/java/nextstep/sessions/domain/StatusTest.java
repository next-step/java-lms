package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StatusTest {
    @Test
    @DisplayName("강의 상태는 PREPARE, RECRUITING, CLOSED 뿐이다")
    void test1(){
        String type = "power recruiting";
        assertThatThrownBy(() -> Status.of(type))
                .hasMessageContaining("허용되지 않은 상태 타입입니다.");
    }
}