package nextstep.courses.domain.image;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImagesTest {
    @Test
    public void 커버_이미지_목록_생성() {
        SessionImage image1 = new SessionImage(100_000L, "png", 300, 200);
        SessionImage image2 = new SessionImage(100_000L, "jpg", 300, 200);

        SessionImages images = new SessionImages(Arrays.asList(image1, image2));

        assertThat(images).isNotNull();
        assertThat(images.size()).isEqualTo(2);
    }

    @Test
    public void 빈_목록으로_생성_불가() {
        assertThatThrownBy(() -> {
            new SessionImages(Collections.emptyList());
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("커버 이미지는 최소 1개 이상이어야 합니다");
    }

    @Test
    public void 대표_이미지_조회() {
        SessionImage image1 = new SessionImage(100_000L, "png", 300, 200);
        SessionImage image2 = new SessionImage(100_000L, "jpg", 300, 200);

        SessionImages images = new SessionImages(Arrays.asList(image1, image2));

        assertThat(images.getFirstImage()).isEqualTo(image1);
    }
}
