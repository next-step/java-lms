package nextstep.sessions.domain;

import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTypeTest {

    @Test
    void 이미지_타입_제한() {
        assertThatThrownBy(() -> ImageType.valueOfName("image.heif"))
            .isInstanceOf(SessionsException.class)
            .hasMessage("지원하지 않는 이미지 타입입니다.");
    }
}
