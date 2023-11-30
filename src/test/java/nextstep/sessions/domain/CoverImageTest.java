package nextstep.sessions.domain;

import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {

    @Test
    void 이미지_타입_제한() {
        assertThatThrownBy(() -> new CoverImage("image.heif", 1048576, 300, 200, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage("지원하지 않는 이미지 타입입니다.");
    }

    @Test
    void 이미지_파일_크기_제한() {
        assertThatThrownBy(() -> new CoverImage("image.png", 1048577, 300, 200, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage("이미지 파일 크기가 초과했습니다.");
    }

    @ParameterizedTest
    @CsvSource(delimiter = ':',
        value = {
            "299:200",
            "300:199",
        })
    void 이미지_가로세로_크기_미달(int width, int height) {
        assertThatThrownBy(() -> new CoverImage("image.png", 1048576, width, height, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage(ImageSize.IMAGE_SIZE_VALIDATION_MESSAGE);
    }

    @Test
    void 이미지_비율_불일치() {
        assertThatThrownBy(() -> new CoverImage("image.png", 1048576, 301, 200, "/images/"))
            .isInstanceOf(SessionsException.class)
            .hasMessage(ImageSize.IMAGE_RATIO_VALIDATION_MESSAGE);
    }

}