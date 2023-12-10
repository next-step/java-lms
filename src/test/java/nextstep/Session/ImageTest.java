package nextstep.Session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ImageTest {

    @Test
    @DisplayName("커버 이미지 생성 성공")
    void createValidImage() {
        ImageDimensions dimensions = new ImageDimensions(300, 200);
        Image image = new Image("test.jpg", 500_000, "jpg", dimensions);
        assertThat(image).isNotNull();
    }

    @Test
    @DisplayName("이미지 크기가 1MB를 초과하면 예외가 발생해야 합니다")
    void imageSizeExceedsLimitThrowsException() {
        ImageDimensions dimensions = new ImageDimensions(300, 200);
        assertThatThrownBy(() -> new Image("test.jpg", 1_500_000, "jpg", dimensions))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("이미지 크기는 1MB를 초과할 수 없습니다.");
    }

}
