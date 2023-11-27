package nextstep.courses.domain;

import nextstep.courses.domain.type.ImageType;
import nextstep.courses.exception.InvalidImageTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageTypeTest {

    @Test
    @DisplayName("이미지 타입 텍스트에 따라 이미지 타입을 찾을 수 있다")
    public void not_recruiting_status_apply() {
        assertThat(ImageType.of("JPG")).isEqualTo(ImageType.JPG);
    }

    @Test
    @DisplayName("존재하지 않는 이미지 타입을 찾을 경우 에러 발생한다")
    public void invalid_image_type() {
        assertThatExceptionOfType(InvalidImageTypeException.class)
            .isThrownBy(() -> ImageType.of("BMP"))
            .withMessageMatching("지원하지 않는 이미지 타입입니다.");
    }
}
