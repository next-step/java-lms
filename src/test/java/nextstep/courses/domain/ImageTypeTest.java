package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;


class ImageTypeTest {

    @ParameterizedTest
    @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
    @DisplayName("이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.")
    void 이미지_타입_체크(String type) {
        assertThatCode(() -> ImageType.isSupportImageType(type))
                .doesNotThrowAnyException();
    }

}