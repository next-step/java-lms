package nextstep.courses;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.exception.ExceedImageRatioException;
import nextstep.courses.exception.ExceedImageSizeException;
import nextstep.courses.exception.ExceedImageWidthHeightException;
import nextstep.courses.exception.InvalidImageExtensionTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static nextstep.courses.constants.ImageSize.*;
import static nextstep.courses.constants.ImageSize.HEIGHT_RATIO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTest {

    @ParameterizedTest
    @ValueSource(strings = {".heic",".java",".jsx",".pdf"})
    @DisplayName("gif, jpg(jpeg 포함), png, svg 이외 확장자로는 이미지를 등록할 수 없다.")
    void invalidExtensionTypeTest(String extensionType) {
        assertThatThrownBy(() -> SessionImage.of(1L, "url", extensionType, 1024L, 300L, 200L))
                .isInstanceOf(InvalidImageExtensionTypeException.class)
                .hasMessage("이미지 타입은 gif, jpg(jpeg 포함), png, svg 만 가능합니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"gif","GIF","JPEG","JPG","jpg","PNG","png","SVG","svg"})
    @DisplayName("gif, jpg(jpeg 포함), png, svg 확장자는 이미지로 등록할 수 있다.")
    void validExtensionTypeTest(String extensionType) {
        SessionImage sessionImage = SessionImage.of(1L, "url", extensionType, 1024L, 300L, 200L);
        assertThat(sessionImage.getExtensionType().name().equalsIgnoreCase(extensionType)).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(longs = {45000, 2400000, 1450000, 2048})
    @DisplayName("이미지는 1MB를 넘을 수 없다.")
    void exceedImageSizeTest(long size) {
        assertThatThrownBy(() -> SessionImage.of(1L, "url", "gif", size, 300L, 200L))
                .isInstanceOf(ExceedImageSizeException.class)
                .hasMessage("이미지 사이즈는 최대 1MB 입니다. 현재사이즈 : " + size);
    }

    @ParameterizedTest
    @ValueSource(longs = {300, 1000, 1024, 450})
    @DisplayName("1MB 이하 이미지는 등록 가능하다.")
    void validImageSizeTest(long size) {
        SessionImage sessionImage = SessionImage.of(1L, "url", "gif", size, 300L, 200L);
        assertThat(sessionImage.getSize()).isEqualTo(size);
    }

    @ParameterizedTest
    @CsvSource(value = {"280:100", "250:300", "100:400", "299:250", "800:100", "600:50"}, delimiter = ':')
    @DisplayName("이미지의 넓이는 300px을 넘고 높이는 200px가 넘어야 한다.")
    void exceedImageWidthTest(long width, long height) {
        assertThatThrownBy(() -> SessionImage.of(1L, "url", "gif", 1024L, width, height))
                .isInstanceOf(ExceedImageWidthHeightException.class)
                .hasMessage("이미지 최소 넓이는 " + MINIMUM_WIDTH + "px, 높이는 최소 " + MINIMUM_HEIGHT + "입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"350:200", "450:250", "600:290", "700:300", "900:500", "1200:300"}, delimiter = ':')
    @DisplayName("이미지의 넓이와 높이가 3:2 비율이 안맞는다면 예외를 발생시킨다.")
    void exceedImageRatioTest(long width, long height) {
        assertThatThrownBy(() -> SessionImage.of(1L, "url", "gif", 1024L, width, height))
                .isInstanceOf(ExceedImageRatioException.class)
                .hasMessage("이미지 넓이와 높이의 비율은 " + WIDTH_RATIO + " : " + HEIGHT_RATIO + "입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"300:200", "600:400", "1200:800", "900:600"}, delimiter = ':')
    @DisplayName("이미지의 넓이와 높이가 3:2 비율이 맞는다면 이미지를 등록할 수 있다.")
    void validImageRatioTest(long width, long height) {
        SessionImage sessionImage = SessionImage.of(1L, "url", "gif", 1024L, width, height);
        assertThat(sessionImage.getUrl()).isEqualTo("url");
    }
}
