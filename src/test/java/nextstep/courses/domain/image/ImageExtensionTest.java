package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.exception.CourseExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ImageExtensionTest {

    @Test
    @DisplayName("[성공] 이미지 확장자 정보를 가져온다")
    void 이미지_확장자() {
        assertThatNoException()
                .isThrownBy(() -> ImageExtension.get("jpg"));
    }

    @Test
    @DisplayName("[실패] 지원하지 않는 이미지 확장자인 경우 SessionCoverImageException 예외가 발생한다.")
    void 이미지_확장자_미지원() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> ImageExtension.get("pdf"))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

}
