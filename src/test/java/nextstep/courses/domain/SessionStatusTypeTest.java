package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionStatusTypeTest {

    @Test
    void testFindSessionStatusType_성공() {
        assertThat(SessionStatusType.find("PREPARING")).isEqualTo(SessionStatusType.PREPARING);
        assertThat(SessionStatusType.find("preparing")).isEqualTo(SessionStatusType.PREPARING);
    }

    @Test
    void testFindSessionStatusType_실패() {
        assertThatThrownBy(() -> {
            SessionStatusType.find("unknown");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 강의 상태 입니다.");

        assertThatThrownBy(() -> {
            SessionStatusType.find("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 강의 상태 입니다.");

        assertThatThrownBy(() -> {
            SessionStatusType.find(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 강의 상태 입니다.");
    }
}