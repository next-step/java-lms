package nextstep.courses.domain.cover;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.error.exception.ImageSizeException;
import org.junit.jupiter.api.Test;

class ImageSizeTest {

    @Test
    void 이미지의_크기는_1MB_이하만_가능하다() {
        ImageSize imageSize = new ImageSize(1);

        assertThat(imageSize.getValue()).isEqualTo(1);
    }

    @Test
    void 이미지의_크기는_1MB_이하가_아닌_경우_예외가_발생한다() {
        assertThatThrownBy(() -> new ImageSize(2))
            .isInstanceOf(ImageSizeException.class)
            .hasMessage("이미지 사이즈의 최대 크기는 1입니다 입력값: 2");
    }
}
