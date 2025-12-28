package nextstep.courses.domain;

import nextstep.courses.domain.session.cover.ImageSize;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.cover.ImageSize.MAX_SIZE;
import static org.assertj.core.api.Assertions.*;

class ImageSizeTest {

    @Test
    @DisplayName("1MB 이하의 이미지 크기는 생성된다")
    void create_image_size() {
        // 방법1
        ImageSize imageSize = new ImageSize(MAX_SIZE);

        assertThat(imageSize).isEqualTo(new ImageSize(MAX_SIZE));

        // 방법2
        assertThatNoException()
                .isThrownBy(() -> new ImageSize(MAX_SIZE));
    }

    @Test
    @DisplayName("1MB보다 큰 이미지 크기는 예외가 발생한다")
    void create_image_size_max_exception() {
        assertThatThrownBy(() -> new ImageSize(MAX_SIZE + 1))
                .isInstanceOf(IllegalArgumentException.class);
    }
}