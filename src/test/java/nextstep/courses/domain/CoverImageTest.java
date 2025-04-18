package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

    @Test
    @DisplayName("커버 이미지 생성 테스트")
    void coverImageSizeTest() {
        new CoverImage(0L, 300, 200, ImageType.JPEG);
    }

    @Test
    @DisplayName("커버 이미지 비율 예외 테스트")
    void coverImageRatioExceptionTest() {
        Assertions.assertThatThrownBy(() -> new CoverImage(0L, 300, 201, ImageType.JPEG))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커버 이미지의 가로 세로 비율은 3:2 이어야 합니다.");
    }
}
