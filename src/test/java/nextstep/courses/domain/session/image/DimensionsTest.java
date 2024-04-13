package nextstep.courses.domain.session.image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DimensionsTest {

    @Test
    @DisplayName("너비와 높이를 기준으로 새로운 커버 이미지 치수를 생성한다.")
    void Dimensions() {
        final Width width = new Width(300);
        final Height height = new Height(200);

        assertThat(new Dimensions(width, height))
                .isEqualTo(new Dimensions(width, height));
    }

    @ParameterizedTest
    @CsvSource(value = {"400,200", "300,300", "300,400"})
    @DisplayName("너비와 높이의 비율이 3:2가 아닌 경우 예외를 던진다.")
    void InValidWidthAndHeightRatio_Exception(final int width, final int height) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Dimensions(new Width(width), new Height(height)));
    }
}
