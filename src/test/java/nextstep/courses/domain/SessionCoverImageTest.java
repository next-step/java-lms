package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionCoverImageTest {

    @Test
    public void 이미지_사이즈가_1MB를_초과하는_경우_예외_발생() {
        long size = 2 * 1024 * 1024;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImage.validateSize(size));
    }

    @Test
    public void 이미지_너비가_300pixel_미만인_경우_예외_발생() {
        int width = 299;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImage.validateWidth(width));
    }

    @Test
    public void 이미지_높이가_200pixel_미만인_경우_예외_발생() {
        int height = 199;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImage.validateHeight(height));
    }

    @ParameterizedTest
    @CsvSource(value = {"500:200", "300:300", "300:201"},delimiter = ':')
    public void 이미지_너비높이_비율이_3대2가_아닌_경우_예외_발생(int width, int height) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImage.validateRatio(width, height));
    }

}
