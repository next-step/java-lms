package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageDimensionTest {

    @Test
    void 이미지_너비_높이_미만_에러발생() {
        assertThatThrownBy(() -> new CoverImage(1, "png", 299, 199))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지_비율_불일치_에러발생() {
        assertThatThrownBy(() -> new CoverImage(1, "png", 400, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
