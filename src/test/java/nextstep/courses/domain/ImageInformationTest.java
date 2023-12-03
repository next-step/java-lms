package nextstep.courses.domain;

import nextstep.courses.domain.image.ImageFormat;
import nextstep.courses.domain.image.ImageInformation;
import nextstep.courses.domain.image.ImageSize;
import nextstep.courses.exception.ExceedLimitedVolumeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ImageInformationTest {

    @DisplayName("이미지가 제한된 용량을 넘을 경우 예외가 발생한다.")
    @Test
    void throw_exception_when_image_exceed_limited_volume() {
        long bytes = 1024 * 1024 * 2;
        ImageSize size = new ImageSize(300, 200);
        ImageFormat format = ImageFormat.GIF;

        assertThatThrownBy(() -> new ImageInformation(size, bytes, format))
                .isInstanceOf(ExceedLimitedVolumeException.class);
    }
}