package nextstep.courses.domain.session.image;


import org.junit.jupiter.api.Test;

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
}
