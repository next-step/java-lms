package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageTypeTest {

    @DisplayName("허용되는 이미지 타입인지 확인한다.")
    @ParameterizedTest
    @MethodSource("checkAllowedImageType")
    void test01(ImageType type, boolean expected) {
        assertThat(ImageType.isAllowed(type)).isEqualTo(expected);
    }

    public static Stream<Arguments> checkAllowedImageType() {
        return Stream.of(
                Arguments.of(ImageType.GIF, true),
                Arguments.of(ImageType.JPG, true),
                Arguments.of(ImageType.JPEG, true),
                Arguments.of(ImageType.PNG, true),
                Arguments.of(ImageType.SVG, true),
                Arguments.of(ImageType.OTHER, false)
        );
    }
}
