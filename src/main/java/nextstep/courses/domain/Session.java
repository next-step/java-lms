package nextstep.courses.domain;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Session {
    private static AtomicLong autoGenId = new AtomicLong(1L);

    private Long id;
    private Long courseId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionStatus sessionStatus;

    public Session(Long courseId, CoverImage coverImage, SessionType sessionType, SessionStatus sessionStatus) {
        this.courseId = courseId;
        this.coverImage = coverImage;
        this.sessionType = sessionType;
        this.sessionStatus = sessionStatus;
    }
}
