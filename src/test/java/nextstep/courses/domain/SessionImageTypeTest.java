package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTypeTest {

    @ParameterizedTest
    @MethodSource("makeValidPath")
    @DisplayName("gif, jpg, jpeg, png, svg 이미지 파일 테스트")
    void testValidType(String filePath, SessionImageType expectSessionImageType) {
        assertThat(SessionImageType.findByFilePath(filePath)).isEqualTo(expectSessionImageType);
    }

    @ParameterizedTest
    @ValueSource(strings = {"image.avi", "/home/image.exe"})
    @DisplayName("올바르지 않은 확장자가 올 경우 에러 발생")
    void testInvalidExtension(String invalidPath) {
        assertThatThrownBy(() -> SessionImageType.findByFilePath(invalidPath)).isInstanceOf(NoSuchElementException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"image", "/home/image", ""})
    @DisplayName("올바르지 않은 파일 경로가 올 경우 에러 발생")
    void testInvalidPath(String invalidPath) {
        assertThatThrownBy(() -> SessionImageType.findByFilePath(invalidPath)).isInstanceOf(IllegalArgumentException.class);
    }


    private static Stream<Arguments> makeValidPath() {
        return Stream.of(
                Arguments.of("image.gif", SessionImageType.GIF),
                Arguments.of("/home/image.jpg", SessionImageType.JPG),
                Arguments.of("image.my.jpeg", SessionImageType.JPEG),
                Arguments.of("image.Png", SessionImageType.PNG),
                Arguments.of("image.SVG", SessionImageType.SVG)
        );
    }
}
