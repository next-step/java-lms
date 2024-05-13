package nextstep.courses.domain.image;

import nextstep.courses.ImageSizeRangeException;
import nextstep.courses.ImageSizeRatioException;
import nextstep.courses.domain.Image.ImageSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageSizeTest {

    @Test
    void 이미지_크기는_가로_300_높이_200_이상이어야_합니다() {
        int width = 299;
        int height = 200;

        Assertions.assertThatThrownBy(() -> new ImageSize(width, height))
            .isInstanceOf(ImageSizeRangeException.class);
    }

    @Test
    void 이미지_비율은_가로_3_세로_2_어야_합니다() {
        int width = 400;
        int height = 200;

        Assertions.assertThatThrownBy(() -> new ImageSize(width, height))
            .isInstanceOf(ImageSizeRatioException.class);
    }

}
