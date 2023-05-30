package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.enums.SessionType;

import java.time.LocalDateTime;

public class Session {
    private Long id;

    private String title;

    private String startDate;

    private String endDate;

    private Capacity capacity;

    private CoverImage coverImage;

    private SessionType sessionType;

    private SessionStatus sessionStatus;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
