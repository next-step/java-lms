package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @Test
    @DisplayName("강의번호 확인 테스트")
    void isSession() {
        final Session session = new Session(1L, null, null, null, null, null, null);

        Assertions.assertThat(session.isSession(2L))
                .isFalse();
    }
}
