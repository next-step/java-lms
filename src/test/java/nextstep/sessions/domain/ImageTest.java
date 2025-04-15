package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {
    @Test
    @DisplayName("이미지 최대 크기를 초과할 시 IllegalArgumentException을 던진다.")
    void 이미지_크기_초과() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() ->
                new Image(2_000_000L, "이미지", ImageType.JPEG, 300F, 200F));
    }

    @Test
    @DisplayName("이미지 최대 크기를 초과하지 않으면 이미지가 잘 생성된다.")
    void 이미지_크기_적정() {
        Image image = new Image(1_000_000L, "이미지", ImageType.JPEG, 300F, 200F);
    }
}
