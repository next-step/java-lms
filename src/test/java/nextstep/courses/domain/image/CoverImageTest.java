package nextstep.courses.domain.image;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CoverImageTest {

    @Test
    void 유효한_이미지는_생성할_수_있다() {
        assertThatNoException().isThrownBy(() ->
            new CoverImage(1L, 1_048_576L, ImageType.JPG, 300, 200)
        );
    }

    @Test
    void 이미지_크기가_1MB를_초과하면_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(1L, 1_048_577L, ImageType.JPG, 300, 200))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지_width가_300픽셀_미만이면_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(1L, 1_048_576L, ImageType.JPG, 299, 200))
            .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void 이미지_height가_200픽셀_미만이면_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(1L, 1_048_576L, ImageType.JPG, 300, 199))
            .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test
    void 이미지_비율이_3대2가_아니면_생성할_수_없다() {
        assertThatThrownBy(() -> new CoverImage(1L, 1_048_576L, ImageType.JPG, 300, 100))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
