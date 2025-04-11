package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionCoverImageTest {

    @Test
    public void 이미지_사이즈가_1MB를_초과하는_경우_예외_발생() {
        long input = 2 * 1024 * 1024;

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCoverImage.validateSize(input));
    }

    @Test
    public void 이미지_너비가_300pixel_미만인_경우_예외_발생() {

    }

    public void 이미지_높이가_200pixel_미만인_경우_예외_발생() {

    }

    @Test
    public void 이미지_너비높이_비율이_3대2가_아닌_경우_예외_발생() {

    }

}
