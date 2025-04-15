package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageTest {
    @Test
    @DisplayName("이미지 최대 크기를 초과할 시 IllegalArgumentException을 던진다.")
    void 이미지_크기_초과() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Image(2_000_000L, "이미지", ImageType.JPEG, 300F, 200F))
                .withMessage("이미지 크기는 최대 1MB 이하여야 합니다.");
    }

    @Test
    @DisplayName("이미지 최대 크기를 초과하지 않으면 이미지가 잘 생성된다.")
    void 이미지_크기_적정() {
        new Image(1_000_000L, "이미지", ImageType.JPEG, 300F, 200F);
    }

    @Test
    @DisplayName("이미지 가로 길이가 300보다 작을 시 IllegalArgumentException을 던진다.")
    void 이미지_최소_가로_크기_부적절() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Image(1_000_000L, "이미지", ImageType.JPEG, 200F, 200F))
                .withMessage("이미지 가로 길이는 최소 300픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이미지 가로 길이가 300이상이면 정상적으로 생성된다.")
    void 이미지_최소_가로_크기_적절() {
        new Image(1_000_000L, "이미지", ImageType.JPEG, 300F, 200F);
    }

    @Test
    @DisplayName("이미지 세로 길이가 200이상이면 정상적으로 생성된다.")
    void 이미지_최소_세로_크기_적절() {
        new Image(1_000_000L, "이미지", ImageType.JPEG, 300F, 200F);
    }
}
