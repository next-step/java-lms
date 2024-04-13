package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = Integer.MAX_VALUE;

    public FreeSession(long id,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt,
                       SessionCoverImage coverImage,
                       SessionType type) {
        super(id, MAXIMUM_NUMBER, startedAt, endedAt, coverImage, type);
    }

    public FreeSession(long id,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt,
                       List<SessionCoverImage> coverImages,
                       SessionType type) {
        super(id, MAXIMUM_NUMBER, startedAt, endedAt, coverImages, type);
    }
}
