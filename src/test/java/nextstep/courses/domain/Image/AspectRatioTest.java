package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AspectRatioTest {
    @Test
    @DisplayName("이미지의 width는 300픽셀 이상 이어야 한다")
    void 생성_픽셀에러_가로() {
        Assertions.assertThatThrownBy(() -> new AspectRatio(200, 300))
                .isInstanceOf(ImageException.class);
    }

    @Test
    @DisplayName("이미지의 height는 200픽셀 이상 이어야 한다")
    void 생성_픽셀에러_세로() {
        Assertions.assertThatThrownBy(() -> new AspectRatio(200, 100))
                .isInstanceOf(ImageException.class);
    }


    @Test
    @DisplayName("width와 height의 비율은 3:2 이어야 한다")
    void 생성_픽셀에러_비율() {
        Assertions.assertThatThrownBy(() -> new AspectRatio(301, 200))
                .isInstanceOf(ImageException.class);
    }


}
