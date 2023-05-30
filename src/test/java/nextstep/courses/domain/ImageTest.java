package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ImageTest {
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    @Test
    void shouldHaveCoverImageInfo() {
        String imgUrl = "img/url.jpg";
        Image image = new Image();
        image.updateUrl(imgUrl);

        assertThat(image.isSameImage(imgUrl)).isTrue();
    }
}
