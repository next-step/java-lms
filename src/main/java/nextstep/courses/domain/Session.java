package nextstep.courses.domain;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;

import java.time.LocalDateTime;

public class Session {
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private CoverImage coverImage;
    private SessionType sessionType;
    private SessionStatus sessionStatus;
}
