package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = Integer.MAX_VALUE;

    public FreeSession(long id, long courseId, List<SessionCoverImage> coverImages, SessionType type) {
        this(id, courseId, LocalDateTime.now(), LocalDateTime.now().plusDays(1), coverImages, type);
    }

    public FreeSession(long id,
                       long courseId,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt,
                       List<SessionCoverImage> coverImages,
                       SessionType type) {
        super(id, courseId, MAXIMUM_NUMBER, startedAt, endedAt, coverImages, type);
    }
}
