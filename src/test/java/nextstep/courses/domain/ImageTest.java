package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class ImageTest {

    @Test
    void 이미지_정상() {
        Image image = new Image("success", 10000, "jpg", 300, 200);
        assertThat(image.getTitle()).isEqualTo("success");
    }

    @DisplayName("이미지 크기는 1MB 이하여야 한다")
    @Test
    void 이미지_크기() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(0L, "image_size", 1048577, "jpg", 300, 200));

    }

    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다")
    @Test
    void 이미지_타입() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(0L, "image_type", 1048576, "bmp", 300, 200));
    }

    @DisplayName("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다")
    @Test
    void 이미지_비율() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(0L, "image_width", 1000, "gif", 301, 200));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(1L, "image_height", 1000, "jpeg", 200, 201));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Image(2L, "image_ratio", 1000, "svg", 300, 199));
    }
}