package nextstep.courses.domain.session.image;

import static nextstep.courses.domain.session.image.DimensionsTest.DIMENSIONS;
import static nextstep.courses.domain.session.image.ImageType.GIF;
import static nextstep.courses.domain.session.image.SizeTest.SIZE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CoverImageTest {

    public static final CoverImage COVER_IMAGE = new CoverImage(1L, GIF, SIZE, DIMENSIONS);

    @Test
    @DisplayName("크기, 종류, 너비, 높이를 기준으로 새로운 커버 이미지를 생성한다.")
    void CoverImage() {
        assertThat(new CoverImage(1L, GIF, SIZE, DIMENSIONS))
                .isEqualTo(COVER_IMAGE);
    }
}
