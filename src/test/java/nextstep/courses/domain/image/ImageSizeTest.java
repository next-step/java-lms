package nextstep.courses.domain.image;

import nextstep.courses.exception.ImageSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {

    @DisplayName("이미지 사이즈를 생성한다")
    @Test
    void 사이즈를_생성한다() {
        // given
        int volume = 300;
        // when
        ImageSize imageSize = new ImageSize(volume);
        // then
        assertThat(imageSize).isEqualTo(new ImageSize(volume));
    }

    @DisplayName("이미지 사이즈가 1MB를 초과하면 예외가 발생한다")
    @Test
    void 이미지_사이즈가_1MB를_초과하면_예외처리한다() {
        // given
        int volume = 1024 * 1024 + 1;
        // when
        // then
        assertThatThrownBy(() -> new ImageSize(volume))
                .isInstanceOf(ImageSizeException.class);
    }

    @DisplayName("이미지 사이즈가 0이하면 예외가 발생한다")
    @Test
    void 이미지_사이즈가_0이하면_예외처리한다() {
        // given
        int volume = 0;
        // when
        // then
        assertThatThrownBy(() -> new ImageSize(volume))
                .isInstanceOf(ImageSizeException.class);
    }
}
