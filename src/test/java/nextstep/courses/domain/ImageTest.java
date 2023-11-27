package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ImageTest {

    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    void imageSizeLessThan1MB() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(1024, 1025, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 가로는 300픽셀 이상이어야한다.")
    void imageWidthSizeEqualsOrMoreThan300() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(299, 1024, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 세로는 200픽셀 이상이어야한다.")
    void imageHeightSizeEqualsOrMoreThan200() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(1024, 199, ImageType.PNG);
        });
    }

    @Test
    @DisplayName("이미지의 가로와 세율의 비율은 2/3 여야한다.")
    void imageRateBetweenWidthAndHeightShouldBe_TwoDividesThree() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Image(400, 200, ImageType.PNG);
        });
    }
}
