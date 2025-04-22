package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import nextstep.courses.domain.session.metadata.coverImage.Dimensions;
import nextstep.courses.domain.session.metadata.coverImage.ImageType;
import nextstep.courses.domain.session.metadata.coverImage.Size;
import nextstep.courses.domain.session.metadata.coverImage.VolumeExceedException;

public class CoverImageTest {
    @Test
    @DisplayName("이미지 크기는 1MB 이하여야 한다.")
    public void coverImageSizeTest() {
        assertThatThrownBy(
            () -> Size.ofKilobytes(1025)
        ).isInstanceOf(VolumeExceedException.class);
    }

    @ParameterizedTest
    @CsvSource({
        "gif,  GIF",
        "jpg,  JPG",
        "jpeg, JPG",
        "png,  PNG",
        "svg,  SVG"
    })
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.")
    public void coverImageTypeTest(String ext, ImageType expectedType) {
        assertThat(ImageType.fromExtension(ext)).isEqualTo(expectedType);
    }

    @Test
    @DisplayName("이미지 타입 호환 안되어야 함")
    public void coverImageUnsupportedTypeTest() {
        assertThatThrownBy(
            () ->ImageType.fromExtension("webp")
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @CsvSource({
        "200, 200",
        "300, 400",
    })
    @DisplayName("이미지 가로세로 실패 테스트")
    public void coverImageDimensionTest(int width, int height) {
        assertThatThrownBy(
            () -> new Dimensions(width, height)
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
