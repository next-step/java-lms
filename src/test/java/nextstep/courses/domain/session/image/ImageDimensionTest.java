package nextstep.courses.domain.session.image;

import nextstep.courses.domain.session.image.ImageDimension;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageDimensionTest {

    @Test
    public void 정상적인_이미지_크기_생성() {
        ImageDimension dimension = new ImageDimension(900, 600);

        assertThat(dimension).isNotNull();
        assertThat(dimension.getWidth()).isEqualTo(900);
        assertThat(dimension.getHeight()).isEqualTo(600);
    }

    @Test
    public void width가_300_미만이면_예외() {
        assertThatThrownBy(() -> {
            new ImageDimension(299, 200);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("width는 300픽셀 이상");
    }

    @Test
    public void height가_200_미만이면_예외() {
        assertThatThrownBy(() -> {
            new ImageDimension(300, 199);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("height는 200픽셀 이상");
    }

    @Test
    public void 비율이_3대2가_아니면_예외() {
        assertThatThrownBy(() -> {
            new ImageDimension(900, 500);  // 9:5
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비율은 3:2");
    }
}

