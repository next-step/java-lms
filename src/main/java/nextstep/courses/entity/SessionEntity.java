package nextstep.courses.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class SessionEntity {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

    private Long courseId;

    private Long fee;

    private int capacity;

    @Deprecated
    private String imageUrl;

    @Deprecated
    private String imageType;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String type;

    private String enrollStatus;

    private String status;

    public String getId() {
        return id.toString();
    }
}
