package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("이미지 확장자 테스트")
class ImageExtensionTest {

    @ParameterizedTest(name = "유효한 이미지 확장자가 아닌 파일명은 예외가 발생한다")
    @ValueSource(strings = {"text.txt", "movie.mp4", "sound.mp3"})
    void InvalidateImageFileExtension(String actualFileName) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() ->
                        ImageExtension.validateExtension(actualFileName))
                .withMessage("not support image extension");
    }

    @ParameterizedTest(name = "유효한 이미지 확장자 파일명은 예외가 발생하지 않는다")
    @CsvSource(value = {"image1.jpeg:jpeg", "image2.png:png"}, delimiter = ':')
    void validateImageFileExtension(String actualFileName, String expectedException) {
        ImageExtension imageExtension = ImageExtension.validateExtension(actualFileName);
        String extension = imageExtension.value();
        Assertions.assertThat(extension).isEqualTo(expectedException);
    }
}
