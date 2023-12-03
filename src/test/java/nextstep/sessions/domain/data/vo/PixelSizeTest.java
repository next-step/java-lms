package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.exception.SessionsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PixelSizeTest {

    @ParameterizedTest
    @CsvSource(delimiter = ':',
        value = {
            "299:200",
            "300:199",
        })
    void 이미지_가로세로_크기_미달(int width, int height) {
        assertThatThrownBy(() -> new PixelSize(width, height))
            .isInstanceOf(SessionsException.class)
            .hasMessage(PixelSize.IMAGE_SIZE_VALIDATION_MESSAGE);
    }

    @Test
    void 이미지_비율_불일치() {
        assertThatThrownBy(() -> new PixelSize(301, 200))
            .isInstanceOf(SessionsException.class)
            .hasMessage(PixelSize.IMAGE_RATIO_VALIDATION_MESSAGE);
    }
}
