package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSizeTest {

    @Test
    void 이미지_파일_크기_제한() {
        assertThatThrownBy(() -> new ImageSize(1048577, 300, 200))
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
        assertThatThrownBy(() -> new ImageSize(1048576, width, height))
            .isInstanceOf(SessionsException.class)
            .hasMessage(ImageSize.IMAGE_SIZE_VALIDATION_MESSAGE);
    }

    @Test
    void 이미지_비율_불일치() {
        assertThatThrownBy(() -> new ImageSize(1048576, 301, 200))
            .isInstanceOf(SessionsException.class)
            .hasMessage(ImageSize.IMAGE_RATIO_VALIDATION_MESSAGE);
    }
}
