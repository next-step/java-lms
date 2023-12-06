package nextstep.courses.domain.course.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {
    @Test
    @DisplayName("이미지는 1 MB 를 초과하면 사이즈가 크다는 예외를 반환한다.")
    void newObject_over1MBSize_throwsException() {
        assertThatThrownBy(
                () -> new Image(2 * Image.MB, "gif", 300, 200)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지는 가로 픽셀이 300 미만이면 길이가 작아는 예외를 반환한다.")
    void newObject_lessThanMinWidthSize_throwsException() {
        assertThatThrownBy(
                () -> new Image(Image.MB, "gif", Image.WIDTH_MIN - 1, Image.HEIGHT_MIN)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지는 세로 픽셀이 200 미만이면 길이가 작아는 예외를 반환한다.")
    void newObject_lessThanMinHeightSize_throwsException() {
        assertThatThrownBy(
                () -> new Image(Image.MB, "gif", Image.WIDTH_MIN, Image.HEIGHT_MIN - 1)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이미지는 가로 세로 비율이 3:2가 아니면 비율이 틀리다는 예외를 반환한다.")
    void newObject_inValidWidthHeightRatio_throwsException() {
        assertThatThrownBy(
                () -> new Image(Image.MB, "gif", 600, 500)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void newObject_inValidType_throwsException() {
        assertThatThrownBy(
                () -> new Image(Image.MB, "pdf", Image.WIDTH_MIN, Image.HEIGHT_MIN)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
