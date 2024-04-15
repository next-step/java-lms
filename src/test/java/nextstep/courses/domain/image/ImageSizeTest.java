package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.exception.CourseExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ImageSizeTest {

    @Test
    @DisplayName("[성공] 이미지 크기 정보를 생성한다.")
    void 이미지_크기() {
        assertThatNoException()
                .isThrownBy(() -> new ImageSize(600, 400));
    }

    @Test
    @DisplayName("[성공][경계값] 이미지 크기 정보를 생성한다.")
    void 이미지_크기_경계값() {
        assertThatNoException()
                .isThrownBy(() -> new ImageSize(300, 200));
    }

    @Test
    @DisplayName("[실패] 이미지 최소 크기를 만족하지 못한 경우 SessionCoverImageException 예외가 발생한다.")
    void 이미지_크기_미만() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new ImageSize(150, 100))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

    @Test
    @DisplayName("[실패] 이미지 크기 비율을 만족하지 못한 경우 SessionCoverImageException 예외가 발생한다.")
    void 이미지_크기_초과() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new ImageSize(400, 600))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

}
