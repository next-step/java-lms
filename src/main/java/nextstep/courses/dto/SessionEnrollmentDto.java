package nextstep.courses.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SessionEnrollmentDto {
    private final Long id;
    private final Long sessionId;
    private final Long userId;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
