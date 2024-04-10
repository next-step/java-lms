package nextstep.courses.domain.session;

import org.springframework.lang.NonNull;

public class Session {

    private final Long id;
    private final Long courseId;
    private final SessionCoverImage coverImage;

    public Session(@NonNull Long id,
                   @NonNull Long courseId,
                   @NonNull SessionCoverImage coverImage) {
        this.id = id;
        this.courseId = courseId;
        this.coverImage = coverImage;
    }

}
