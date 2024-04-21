package nextstep.courses.entity;

import java.time.LocalDateTime;
import nextstep.courses.domain.BaseEntity;
import nextstep.courses.domain.cover.Image;

public class ImageEntity extends BaseEntity {

    private Long id;

    private int size;

    private String type;

    private int width;

    private int height;

    private Long sessionId;

    public static ImageEntity from(Image image, Long sessionId) {
        return new ImageEntity(image.getId(), image.getSize(), image.getType(), image.getWidth(),
            image.getHeight(), sessionId, image.getCreatedAt(), image.getUpdatedAt());
    }

    public ImageEntity(Long id, int size, String type, int width, int height, Long sessionId,
        LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
        this.sessionId = sessionId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Long getSessionId() {
        return sessionId;
    }
}
