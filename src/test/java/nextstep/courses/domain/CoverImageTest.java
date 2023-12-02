package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeOverException;
import nextstep.courses.exception.InvalidImageExtensionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CoverImageTest {

    @Test
    @DisplayName("이미지 크기가 1(MB)이상일 경우 예외를 던진다.")
    void size_test() {
        assertThatThrownBy(() -> new CoverImage(2, "gif"))
                .isInstanceOf(ImageSizeOverException.class)
                .hasMessageContaining("이미지 크기는 1MB를 초과할 수 없습니다.");
    }

    @Test
    @DisplayName("이미지 타입이 gif, jpg(jpeg 포함), png, svg이 아니면 예외를 던진다.")
    void extension_test_invalid() {
        assertThatThrownBy(() -> new CoverImage(1, "hwp"))
                .isInstanceOf(InvalidImageExtensionException.class)
                .hasMessageContaining("gif, jpg(jpeg 포함), png, svg 확장자만 허용됩니다.");
    }

    @Test
    @DisplayName("이미지 타입이 gif, jpg(jpeg 포함), png, svg이면 예외를 던지지 않는다.")
    void extension_test_valid() {
        assertDoesNotThrow(() -> new CoverImage(1, "jpg"));
    }
}
