package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private Long sessionId;

    private String title;

    private CourseType courseType;

    private SessionStatus sessionStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    private Image coverImage;

}
