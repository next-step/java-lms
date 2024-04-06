package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionCoverImageTest {

    @DisplayName("이미지 용량이 1MB 보다 크다면 예외가 발생한다.")
    @Test
    void test01() {
        int sizeOfBytes = SessionCoverImage.MAX_SIZE + 1;
        assertThatThrownBy(() -> new SessionCoverImage(sizeOfBytes, ImageType.GIF, 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 용량은 1MB 이하여야 합니다.");
    }
}
