package nextstep.course;

import nextstep.lecture.Image;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
- 이미지를 생성한다.
- 이미지가 1MB를 초과하면 예외가 발생한다.
- 이미지의 너비가 300px, 높이가 200px을 초과하면 예외가 발생한다.
- 이미지의 너비 높이가 3:2 비율이 아니라면 예외가 발생한다.
*/
public class ImageTest {

    @DisplayName("이미지를 생성한다.")
    @Test
    void createImageTest() {
        Image image = new Image(1L, "테스트이미지.jpg", 300, 200, 1);

        assertThat(image)
                .extracting("name", "width", "height", "size")
                .contains("테스트이미지.jpg", 300, 200, 1);
    }

    @DisplayName("이미지가 1MB를 초과하면 예외가 발생한다.")
    @Test
    void createImageOverSizeThrowExceptionTest() {

        assertThatThrownBy(() -> new Image(1L, "테스트이미지.jpg", 300, 200, 2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 사이즈는 1MB를 초과하면 안됩니다.");
    }

    @DisplayName("이미지의 너비가 300px, 높이가 200px을 초과하면 예외가 발생한다.")
    @Test
    void createImageOverWidthHeightThrowExceptionTest() {

        assertThatThrownBy(() -> new Image(1L, "테스트이미지.jpg", 600, 400, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 너비가 300px, 높이가 200px을 초과하면 안됩니다.");
    }

    @DisplayName("이미지의 너비 높이가 3:2 비율이 아니라면 예외가 발생한다.")
    @Test
    void createImageRateThrowExceptionTest() {

        assertThatThrownBy(() -> new Image(1L, "테스트이미지.jpg", 200, 200, 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 너비 높이가 3:2 비율이여야 합니다.");
    }
}
