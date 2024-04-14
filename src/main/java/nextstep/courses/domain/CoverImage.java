package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CoverImage extends BaseTimeEntity{

    private final ImageCapacity capacity;
    private final ImageType type;
    private final ImageDimension dimension;
    private final Long sessionId;
    private final Long id;

    public CoverImage(Long capacity, String type, Long width, Long hegith , Long sessionId, Long id){
        this(LocalDateTime.now(), LocalDateTime.now(), capacity, type, width, hegith, sessionId, id);
    }

    public CoverImage(ImageCapacity capacity, ImageType type, ImageDimension dimension) {
        this(LocalDateTime.now(), LocalDateTime.now(), capacity, type, dimension, 0L, 0L);
    }

    public CoverImage(LocalDateTime createdAt, LocalDateTime updatedAt, Long capacity, String type, Long width, Long height, Long sessionId, Long id) {
        this(createdAt, updatedAt, new ImageCapacity(capacity), ImageType.getImageType(type), new ImageDimension(width, height), sessionId, id);
    }

    private CoverImage(LocalDateTime createdAt, LocalDateTime updatedAt, ImageCapacity capacity, ImageType type, ImageDimension dimension, Long sessionId, Long id) {
        super(createdAt, updatedAt);
        this.capacity = capacity;
        this.type = type;
        this.dimension = dimension;
        this.sessionId = sessionId;
        this.id = id;
    }

    public ImageCapacity getCapacity() {
        return capacity;
    }

    public ImageType getType() {
        return type;
    }

    public ImageDimension getDimension() {
        return dimension;
    }

    public Long getSessionId() {
        return sessionId;
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "id=" + id +
                ", sessionId=" + sessionId +
                ", capacity=" + capacity.getCapacity() +
                ", type=" + type.name() +
                ", dimension.width=" + dimension.getWidth() +
                ", dimension.height=" + dimension.getHeight() +
                '}';
    }
}
