package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

    @Test
    @DisplayName("커버 이미지 생성 테스트")
    void coverImageSizeTest() {
        new CoverImage(0L, 300, 200, ImageType.JPEG);
    }
}
