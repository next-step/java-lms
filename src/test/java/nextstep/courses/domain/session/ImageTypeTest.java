package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ImageTypeTest {
    @ParameterizedTest
    @MethodSource("imageTypeProvider")
    @DisplayName("string을 넣으면 해당하는 타입을 찾아준다.")
    void testImageTypeFromStringWithNormalString(final String text, final ImageType expected) {
        //given, when
        ImageType imageType = ImageType.fromString(text);

        //then
        assertThat(imageType).isEqualTo(expected);
    }

    public static Stream<Arguments> imageTypeProvider() {
        return Stream.of(
                Arguments.of("jpg", ImageType.JPG),
                Arguments.of("jpeg", ImageType.JPEG),
                Arguments.of("png", ImageType.PNG),
                Arguments.of("gif", ImageType.GIF),
                Arguments.of("Svg", ImageType.SVG),
                Arguments.of("sVg", ImageType.SVG),
                Arguments.of("svG", ImageType.SVG)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"jag", "jpag", "ppng"})
    @DisplayName("잘못된 이름을 넣으면, 예외가 발생한다.")
    void testImageTypeFromStringWithWrongString(final String text) {
        //given, when, then
        assertThatThrownBy(() -> ImageType.fromString(text))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(text + "is not use. only usable type : jpg, jpeg, png, gif, svg");
    }
}
