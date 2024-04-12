package nextstep.courses.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionStatusTest {

    @Test
    @DisplayName("isStatusNotRecruiting 호출 시 상태가 모집 중이 아니면 true")
    void isStatusNotRecruiting() {
        SessionStatus ready = SessionStatus.READY;
        SessionStatus recruiting = SessionStatus.RECRUITING;
        assertThat(ready.isStatusNotRecruiting()).isTrue();
        assertThat(recruiting.isStatusNotRecruiting()).isFalse();
    }
}
