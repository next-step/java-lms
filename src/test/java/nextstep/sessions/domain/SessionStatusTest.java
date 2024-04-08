package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {

    @DisplayName("강의 상태가 모집중이 아닌지를 반환한다")
    @Test
    void isNotRecruiting() {
        assertThat(SessionStatus.isNotRecruiting(SessionStatus.END)).isTrue();
    }

}
