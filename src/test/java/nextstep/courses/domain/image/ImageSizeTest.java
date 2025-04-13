package nextstep.courses.domain.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageSizeTest {

    @Test
    @DisplayName("정상적인 이미지 크기를 생성한다")
    void create() {
        // given
        int width = 300;
        int height = 200;

        // when
        ImageSize imageSize = new ImageSize(width, height);

        // then
        assertThat(imageSize).isNotNull();
        assertThat(imageSize.getWidth()).isEqualTo(width);
        assertThat(imageSize.getHeight()).isEqualTo(height);
    }

    @ParameterizedTest
    @CsvSource({
        "299, 200, '이미지는 최소 300x200 픽셀 이상이어야 합니다.'",
        "300, 199, '이미지는 최소 300x200 픽셀 이상이어야 합니다.'"
    })
    @DisplayName("이미지 크기가 최소 크기보다 작으면 예외가 발생한다")
    void validateDimensions(int width, int height, String expectedMessage) {
        // when & then
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedMessage);
    }

    @Test
    @DisplayName("이미지 비율이 3:2가 아니면 예외가 발생한다")
    void validateAspectRatio() {
        // given
        int width = 300;
        int height = 201; // 300:201는 3:2 비율이 아님

        // when & then
        assertThatThrownBy(() -> new ImageSize(width, height))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지는 3:2 비율이어야 합니다.");
    }

    @Test
    @DisplayName("3:2 비율의 이미지를 생성할 수 있다")
    void createWithValidAspectRatio() {
        // given
        int width = 600;
        int height = 400; // 600:400은 3:2 비율

        // when
        ImageSize imageSize = new ImageSize(width, height);

        // then
        assertThat(imageSize).isNotNull();
        assertThat(imageSize.getWidth()).isEqualTo(width);
        assertThat(imageSize.getHeight()).isEqualTo(height);
    }
} 