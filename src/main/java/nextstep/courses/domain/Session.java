package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private Long id;

    private String title;

    private Long creatorId;

    private Image coverImage;

    private SessionType type;

    private SessionStatus status;

    private Fee fee;

    private int maxStudents;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Session() {
    }


}
