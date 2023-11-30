package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {
    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    void 생성_이미지크기_에러() {
        assertThatThrownBy(() -> new CoverImage(1000_001, "gif", 400, 600))
                .isInstanceOf(ImageException.class);
    }
}
