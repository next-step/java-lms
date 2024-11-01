package nextstep.courses.domain.cover;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageExtensionTest {

    @DisplayName("유효한 확장자인 경우 false를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"jpg", "jpeg", "png", "gif", "svg"})
    void validExtensionTest(String extension) {
        assertThat(ImageExtension.isInvalidImageExtension(extension)).isFalse();
    }

    @DisplayName("유효하지 않은 확장자인 경우 true를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"bmp", "tiff", "pdf", "txt", "exe"})
    void invalidExtensionTest(String extension) {
        assertThat(ImageExtension.isInvalidImageExtension(extension)).isTrue();
    }

    @DisplayName("유효한 확장자가 대문자라도 true 를 반환한다.")
    @ParameterizedTest
    @ValueSource(strings = {"JPG", "JPEG", "PNG", "GIF", "SVG"})
    void upperCaseExtensionTest(String extension) {
        assertThat(ImageExtension.isInvalidImageExtension(extension)).isFalse();
    }

    @DisplayName("유효하지 않은 확장자인 경우 예외가 발생한다.")
    @Test
    void getExtensionExceptionTest() {
        assertThatThrownBy(
                () -> ImageExtension.getExtension("hwp")
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("허용되지 않는 이미지 형식입니다.");
    }

}
