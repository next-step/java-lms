package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.exception.CourseExceptionMessage.UNSUPPORTED_IMAGE_FORMAT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class ImageFileSizeTest {

    @Test
    @DisplayName("[성공] 이미지 파일 크기 정보를 생성한다.")
    void 이미지_파일_크기() {
        assertThatNoException()
                .isThrownBy(() -> new ImageFileSize(1024*1000));
    }

    @Test
    @DisplayName("[성공][경계값] 이미지 파일 크기 정보를 생성한다.")
    void 이미지_파일_크기_경계값() {
        assertThatNoException()
                .isThrownBy(() -> new ImageFileSize(1024*1024));
    }

    @Test
    @DisplayName("[실패] 이미지 파일 크기를 초과한 경우 SessionCoverImageException 예외가 발생한다.")
    void 이미지_파일_크기_초과() {
        assertThatExceptionOfType(SessionCoverImageException.class)
                .isThrownBy(() -> new ImageFileSize(1024*1025))
                .withMessageContaining(UNSUPPORTED_IMAGE_FORMAT.getMessage());
    }

}
