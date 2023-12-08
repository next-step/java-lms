package nextstep.courses.domain;

import nextstep.courses.type.SessionDuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionDurationTest {
    @Test
    @DisplayName("[SessionDuration.of()] 종료 시각이 생성 시각보다 빠를 수 없음")
    public void validationTest() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            SessionDuration.fromIso8601("2023-12-06T10:20:00.000", "2023-12-06T10:19:59.000");
        });
    }
}