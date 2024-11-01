package nextstep.courses.domain.cover;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageSizeTest {


    @DisplayName("유효한 파일 크기로 ImageSize 객체를 생성한다.")
    @Test
    void createImageSizeTest() {
        int fileSize = 500 * 1024;
        ImageSize imageSize = ImageSize.of(fileSize);
        assertThat(imageSize.getImageSize()).isEqualTo(fileSize);
    }

    @DisplayName("최대 파일 크기보다 큰 경우 예외가 발생한다.")
    @Test
    void throwExceptionWithOverSize() {
        int fileSize = 2 * 1024 * 1024;

        Assertions.assertThatThrownBy(
                        () -> ImageSize.of(fileSize)
                ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
    }
}
