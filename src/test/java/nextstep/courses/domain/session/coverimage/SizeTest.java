package nextstep.courses.domain.session.coverimage;

import nextstep.courses.domain.session.coverimage.Size;
import nextstep.courses.exception.ImageSizeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

public class SizeTest {

    @DisplayName("이미지의 가로-세로 비율이 3:2가 아니면 예외를 던진다.")
    @ParameterizedTest
    @CsvSource({"600,300,3:1.5", "567,321,3:1.698"})
    void validateAspectRatio(int width, int height, String expectedRatio) {
        assertThatThrownBy(() -> new Size(width, height)).isInstanceOf(ImageSizeException.class)
            .hasMessage("이미지의 가로-세로 비율은 3:2 이어야 합니다. 현재 비율 :: " + expectedRatio);
    }
}
