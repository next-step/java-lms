package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("도메인 객체 ImageType 테스트")
class ImageTypeTest {

    @DisplayName("이미지 gif 확장자 검증 테스트")
    @Test
    void imageTypeGifValidTest() {
        assertThat(ImageType.isValidType("gif")).isTrue();
    }

    @DisplayName("이미지 jpeg 확장자 검증 테스트")
    @Test
    void imageTypeJpegValidTest() {
        assertThat(ImageType.isValidType("jpeg")).isTrue();
    }

    @DisplayName("이미지 jpg 확장자 검증 테스트")
    @Test
    void imageTypeJpgValidTest() {
        assertThat(ImageType.isValidType("jpg")).isTrue();
    }

    @DisplayName("이미지 png 확장자 검증 테스트")
    @Test
    void imageTypePngValidTest() {
        assertThat(ImageType.isValidType("png")).isTrue();
    }

    @DisplayName("이미지 svg 확장자 검증 테스트")
    @Test
    void imageTypeSvgValidTest() {
        assertThat(ImageType.isValidType("svg")).isTrue();
    }

    @DisplayName("이미지 비허용 확장자 검증 테스트")
    @Test
    void imageTypeInvalidTest() {
        assertThat(ImageType.isValidType("zip")).isFalse();
    }
}
