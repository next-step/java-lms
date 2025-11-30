package nextstep.courses.domain;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTest {

    @Test
    public void 정상적인_이미지_생성() {
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);

        assertThat(image).isNotNull();
    }

    @Test
    public void 이미지_크기가_1MB_초과하면_예외발생() {
        long overSize = 1024 * 1024 + 1;

        assertThatThrownBy(() -> {
            new SessionImage(overSize, "png", 900, 600);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이미지 크기는 1MB 이하여야 한다");
    }

    @Test
    public void 허용되지_않는_이미지_타입이면_예외() {
        assertThatThrownBy(() -> {
            new SessionImage(500_000L, "bmp", 300, 200);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("지원하지 않는 이미지 타입");
    }

    @Test
    public void width가_300_미만이면_예외() {
        assertThatThrownBy(() -> {
            new SessionImage(500_000L, "png", 299, 200);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("width는 300픽셀 이상");
    }

    @Test
    public void height가_200_미만이면_예외() {
        assertThatThrownBy(() -> {
            new SessionImage(500_000L, "png", 300, 199);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("height는 200픽셀 이상");
    }


    @Test
    public void 비율이_3대2가_아니면_예외() {
        assertThatThrownBy(() -> {
            new SessionImage(500_000L, "png", 900, 500);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비율은 3:2");
    }

    @ParameterizedTest
    @ValueSource(strings = {"jpg", "jpeg", "gif", "svg", "png"})
    public void 허용타입_허용(String imageType) {
        assertThat(new SessionImage(500_000L, imageType, 300, 200)).isNotNull();
    }
}
