package nextstep.courses.domain.entity;

import java.time.LocalDateTime;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionType;

public class Session {

    private CoverImage coverImage;

    private SessionType sessionType;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
