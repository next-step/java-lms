package nextstep.courses.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ImageDto {
    private final Long id;
    private final Long sessionId;
    private final String fileName;
    private final Long fileSize;
    private final int width;
    private final int height;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}