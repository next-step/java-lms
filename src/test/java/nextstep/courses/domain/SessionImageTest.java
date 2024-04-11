package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionImageTest {

    private final Long validId = 1L;
    private final int validHeight = 200;
    private final int validWidth = 300;
    private final int validSize = 1;
    private final String validPath = "image.png";

    @Test
    @DisplayName("강의 이미지 생성 테스트")
    void testSessionImage() {
        SessionImage sessionImage = new SessionImage(validId,validPath, validWidth, validHeight, validSize);

        assertThat(sessionImage.getId()).isEqualTo(validId);
        assertThat(sessionImage.getPath()).isEqualTo(validPath);
    }
}
