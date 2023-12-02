package nextstep.courses.domain;

import nextstep.courses.exception.SessionImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTest {
    public static SessionImage normalSessionImage() {
        return new SessionImage(1000,"jpg",300,200);
    }

    @Test
    @DisplayName("실패 - 강의 커버 이미지 크기가 1MB 이하가 아닐 경우 예외가 발생 한다.")
    void fail_session_image_size_over() {
        int size = 1001;
        assertThatThrownBy(() -> new SessionImage(size, "jpg", 300, 200))
                .isInstanceOf(SessionImageException.class)
                .hasMessage("강의 커버 이미지 사이즈는 1MB 이하여야 합니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"gif", "jpg", "jpeg", "png", "svg"})
    @DisplayName("성공 - 강의 커버 이미지 확장자가 gif, jpg(jpeg 포함), png, svg인 경우 예외가 발생하지 않는다.")
    void success_session_image_extension_contain(String extension) {
        assertThatCode(() -> new SessionImage(1000, extension, 300, 200))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "이미지 확장자 : {0}")
    @CsvSource(value = {"pdf", "psd", "ai", "tiff", "bmp", "eps"})
    @DisplayName("실패 - 강의 커버 이미지 확장자가 gif, jpg(jpeg 포함), png, svg가 아닐 경우 예외가 발생 한다.")
    void fail_session_image_extension_not_contain(String extension) {
        assertThatThrownBy(() -> new SessionImage(1000, extension, 300, 200))
                .isInstanceOf(SessionImageException.class)
                .hasMessage("강의 커버 이미지 확장자는 gif, jpg(jpeg 포함), png, svg만 허용됩니다.");
    }

    @ParameterizedTest(name = "가로 길이 : {0}, 세로 길이 : {1}")
    @CsvSource(value = {"299:200", "300:199", "299:199"}, delimiter = ':')
    @DisplayName("실패 - width는 300픽셀, height는 200픽셀 이상이 아닐 경우 예외가 발생한다.")
    void fail_session_image_length_not_contain(int width, int height) {
        assertThatThrownBy(() -> new SessionImage(1000, "jpg", width, height))
                .isInstanceOf(SessionImageException.class)
                .hasMessage("이미지의 width 300픽셀, height는 200픽셀 이상이여야 합니다.");
    }

    @Test
    @DisplayName("성공 - width는 300픽셀, height는 200픽셀 이상일 경우 예외가 발생하지 않는다.")
    void success_session_image_length_contain() {
        assertThatCode(() -> new SessionImage(1000, "jpg", 300, 200))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "가로 길이 : {0}, 세로 길이 : {1}")
    @CsvSource(value = {"300:201", "301:200"}, delimiter = ':')
    @DisplayName("실패 - width와 height의 비율이 3:2 아닐 경우 예외가 발생한다.")
    void fail_session_image_length_ratio_not_contain(int width, int height) {
        assertThatThrownBy(() -> new SessionImage(1000, "jpg", width, height))
                .isInstanceOf(SessionImageException.class)
                .hasMessage("이미지의 width와 height의 비율은 3:2여야 합니다.");
    }

}
