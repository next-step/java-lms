package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionImageSizeTest {

    private final int validWidth = 300;
    private final int validHeight = 200;
    private final int validSize = 1;

    @Test
    @DisplayName("이미지 사이즈 생성 테스트")
    void testValidSessionImageSize() {
        SessionImageSize sessionImageSize = new SessionImageSize(validWidth, validHeight, validSize);

        assertThat(sessionImageSize).extracting(SessionImageSize::getSize).isEqualTo(validSize);
        assertThat(sessionImageSize).extracting(SessionImageSize::getWidth).isEqualTo(validWidth);
        assertThat(sessionImageSize).extracting(SessionImageSize::getHeight).isEqualTo(validHeight);
    }

    @Test
    @DisplayName("이미지 비율이 3:2가 아닌 경우 에러 발생")
    void testInvalidRatio() {
        int width = 300;
        int height = 300;

        assertThatIllegalArgumentException().isThrownBy(() -> new SessionImageSize(width, height, validSize));
    }

    @ParameterizedTest
    @CsvSource(value = {"30,20", "180,120"})
    void testInvalidRatio(int width, int height) {
        assertThatIllegalArgumentException().isThrownBy(() -> new SessionImageSize(width, height, validSize));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2, 3})
    @DisplayName("이미지 사이즈가 1MB 초과하거나 0보다 작은 경우 에러 발생")
    void testInvalidSize(int size) {
        assertThatIllegalArgumentException().isThrownBy(() -> new SessionImageSize(validWidth, validHeight, size));
    }

}
