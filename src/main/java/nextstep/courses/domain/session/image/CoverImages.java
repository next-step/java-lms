package nextstep.courses.domain.session.image;

import java.util.Collections;
import java.util.List;

public class CoverImages {
    public static final String COVER_IMAGE_IS_EMPTY = "최소 1개 이상의 강의 커버 이미지를 가져야 합니다.";

    private final List<CoverImage> coverImages;

    private CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public static CoverImages of(List<CoverImage> coverImages) {
        return new CoverImages(coverImages);
    }

    public List<CoverImage> getCoverImages() {
        return Collections.unmodifiableList(coverImages);
    }

    public void addCoverImage(CoverImage coverImage) {
        coverImages.add(coverImage);
    }

    public static boolean hasOneCoverImageAtLeast(List<CoverImage> coverImages) {
        return !coverImages.isEmpty();
    }
}
