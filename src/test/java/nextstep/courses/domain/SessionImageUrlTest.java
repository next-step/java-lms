package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionImageUrlTest {
    @Test
    @DisplayName("URL 형식이 아닌 경우 예외가 발생한다")
    void validUrl() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new SessionImageUrl("test"))
                .withMessageMatching(SessionImageUrl.INVALID_URL_MESSAGE);
    }
}
