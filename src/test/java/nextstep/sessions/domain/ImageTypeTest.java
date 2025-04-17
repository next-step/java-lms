package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ImageTypeTest {

    @Test
    @DisplayName("지원하는 이미지 타입은 enum으로 정상 변환된다.")
    void 지원하는_이미지_타입_변환_성공() {
        assertThat(ImageType.from("gif")).isEqualTo(ImageType.GIF);
        assertThat(ImageType.from("jpg")).isEqualTo(ImageType.JPG);
        assertThat(ImageType.from("jpeg")).isEqualTo(ImageType.JPEG);
        assertThat(ImageType.from("png")).isEqualTo(ImageType.PNG);
        assertThat(ImageType.from("svg")).isEqualTo(ImageType.SVG);
    }

    @Test
    @DisplayName("지원하지 않는 이미지 타입은 IllegalArgumentException을 던진다.")
    void 지원하지_않는_이미지_타입_예외() {
        assertThatIllegalArgumentException().isThrownBy(() -> ImageType.from("java"))
                .withMessage("지원하지 않는 이미지 타입입니다.");
    }
}
