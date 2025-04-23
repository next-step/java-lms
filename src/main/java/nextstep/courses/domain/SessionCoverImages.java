package nextstep.courses.domain;

import java.util.List;
import java.util.stream.Collectors;

public class SessionCoverImages {
    private final List<SessionCoverImage> sessionCoverImages;

    public SessionCoverImages(List<SessionCoverImage> sessionCoverImages) {
        this.sessionCoverImages = sessionCoverImages;
    }

    public static SessionCoverImages from(List<String> coverImagePaths) {
        return new SessionCoverImages(coverImagePaths.stream()
                .map(SessionCoverImage::from)
                .collect(Collectors.toList()));
    }
}
