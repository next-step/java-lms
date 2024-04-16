package nextstep.courses.domain.session.image;

import static nextstep.courses.domain.session.image.ImageType.GIF;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoverImageTest {

    @Test
    @DisplayName("크기, 종류, 너비, 높이를 기준으로 새로운 커버 이미지를 생성한다.")
    void CoverImage() {
        final Size size = new Size(1000L);
        final Dimensions dimensions = new Dimensions(new Width(300), new Height(200));

        assertThat(new CoverImage(1L, GIF, size, dimensions, 1L))
                .isEqualTo(new CoverImage(1L, GIF, size, dimensions, 1L));
    }
}
