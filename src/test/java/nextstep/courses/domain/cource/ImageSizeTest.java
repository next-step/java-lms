package nextstep.courses.domain.cource;

import nextstep.courses.domain.session.ImageSize;
import nextstep.courses.exception.image.ImageSizeBelowMinException;
import nextstep.courses.exception.image.UnsupportedImageRatioException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    @DisplayName("이미지 크기는 width 300, height 200 이상이어야 하며, 비율은 3:2 이여야 한다.")
    public void check_imageSize_and_ratio() {
        Assertions.assertThatThrownBy(() -> ImageSize.of(299L, 200L)).isInstanceOf(ImageSizeBelowMinException.class);

        Assertions.assertThatThrownBy(() -> ImageSize.of(299L, 199L)).isInstanceOf(ImageSizeBelowMinException.class);

        Assertions.assertThatThrownBy(() -> ImageSize.of(300L, 201L)).isInstanceOf(UnsupportedImageRatioException.class);
    }
}
