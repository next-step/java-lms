package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionStatusTest {

    @Test
    @DisplayName("강의가 모집중인지 확인한다.")
    void check_session_status_recruiting() {
        assertThat(SessionStatus.isRecruiting(SessionStatus.RECRUITING)).isTrue();
    }

}
