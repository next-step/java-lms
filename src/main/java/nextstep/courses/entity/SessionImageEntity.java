package nextstep.courses.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class SessionImageEntity {
    private Long id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private boolean deleted;

    private String imageUrl;

    private String imageType;

    private Long sessionId;

    public String getId() {
        return id.toString();
    }
}
