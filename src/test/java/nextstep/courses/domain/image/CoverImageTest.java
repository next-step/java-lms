package nextstep.courses.domain.image;

import nextstep.courses.domain.image.constant.ImageType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * 이미지 크기는 1MB 이하여야 한다. -> 1024 * 1024
 * 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
 * 이미지의 width는 300px, height는 200px 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
 */
public class CoverImageTest {

    @Test
    void 이미지_정상_생성() {
        CoverImage image = new CoverImage(1, "gif", 300, 200);
        assertThat(image.getSize()).isEqualTo(1);
        assertThat(image.getType()).isEqualTo(ImageType.GIF);
    }

    @Test
    void 이미지_타입_불일치_에러발생() {
        assertThatThrownBy(() -> new CoverImage(1, "mp4", 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이미지_크기_초과_에러발생(){
        assertThatThrownBy(() -> new CoverImage(1024 * 1070, "png", 300, 200))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
