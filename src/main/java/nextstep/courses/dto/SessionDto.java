package nextstep.courses.dto;

import lombok.Builder;
import lombok.Getter;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.domain.session.SessionType;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class SessionDto {
    private final Long id;
    private final Long courseId;
    private final String title;
    private final SessionType sessionType;
    private final SessionStatus status;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final int maximumEnrollment;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
