package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = Integer.MAX_VALUE;

    public FreeSession(long id,
                       long courseId) {
        this(id, courseId, new ArrayList<>(), LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }

    public FreeSession(long id,
                       long courseId,
                       List<Long> coverImageIds) {
        this(id, courseId, coverImageIds, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
    }


    public FreeSession(long id,
                       long courseId,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt) {
        this(id, courseId, new ArrayList<>(), startedAt, endedAt);
    }

    public FreeSession(long id,
                       long courseId,
                       List<Long> coverImageIds,
                       LocalDateTime startedAt,
                       LocalDateTime endedAt) {
        super(id, courseId, coverImageIds, MAXIMUM_NUMBER, startedAt, endedAt, SessionType.FREE);
    }
}
