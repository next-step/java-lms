package nextstep.courses.domain.session;

import nextstep.courses.domain.cover.CoverImage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CoverImageHelper {

    public static CoverImage getSingleCoverImage(List<CoverImage> coverImages) {
        assertThat(coverImages).isNotNull();
        assertThat(coverImages).hasSize(1);
        return coverImages.get(0);
    }
}
