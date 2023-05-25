package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private String coverImageUrl;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private SessionType type;
    private SessionStatus status;
    private int capacity;
}
