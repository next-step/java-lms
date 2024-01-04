package nextstep.courses.domain.session;

import java.util.List;

public class CoverImages {
    private final List<CoverImage> coverImages;

    public CoverImages(List<CoverImage> coverImages) {
        this.coverImages = coverImages;
    }

    public boolean hasSameSessionIds(Long sessionId) {
        return coverImages.stream().allMatch(coverImage -> coverImage.hasSameSessionId(sessionId));
    }
}
