package nextstep.courses.domain;

import nextstep.courses.exception.ImageOverVolumeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {
    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    void 생성_이미지크기_에러() {
        long volume = 1000_001;
        String type = "gif";
        int height = 400;
        int width = 600;
        Assertions.assertThatThrownBy(() -> new CoverImage(volume, type, height, width))
                .isInstanceOf(ImageOverVolumeException.class);
    }
}
