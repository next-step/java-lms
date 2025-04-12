package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

    @Test
    @DisplayName("커버 이미지 생성 테스트")
    void coverImageSizeTest() {
        new CoverImage();
    }

    @Test
    @DisplayName("커버 이미지 크기는 1MB 이하여야 한다.")
    void coverImageSizeOverTest() {
        new CoverImage(1_048_576L);
        Assertions.assertThatThrownBy(() -> new CoverImage(1_048_577L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커버 이미지의 크기는 1MB 이하여야 합니다.");;
    }
}
