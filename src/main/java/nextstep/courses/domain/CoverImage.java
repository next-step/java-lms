package nextstep.courses.domain;

import java.time.LocalDateTime;

public class CoverImage extends BaseTimeEntity {

    private final ImageCapacity capacity;
    private final ImageType type;
    private final ImageDimension dimension;
    private Session session;
    private final Long id;

    public CoverImage(Long capacity, String type, Long width, Long hegith, Session session, Long id) {
        this(LocalDateTime.now(), LocalDateTime.now(), capacity, type, width, hegith, session, id);
    }

    public CoverImage(ImageCapacity capacity, ImageType type, ImageDimension dimension) {
        this(LocalDateTime.now(), LocalDateTime.now(), capacity, type, dimension, null, 0L);
    }

    public CoverImage(LocalDateTime createdAt, LocalDateTime updatedAt, Long capacity, String type, Long width, Long height, Session session, Long id) {
        this(createdAt, updatedAt, new ImageCapacity(capacity), ImageType.getImageType(type), new ImageDimension(width, height), session, id);
    }

    private CoverImage(LocalDateTime createdAt, LocalDateTime updatedAt, ImageCapacity capacity, ImageType type, ImageDimension dimension, Session session, Long id) {
        super(createdAt, updatedAt);
        this.capacity = capacity;
        this.type = type;
        this.dimension = dimension;
        this.session = session;
        this.id = id;
    }

    public void changeSession(Session session) {
        this.session = session;
        session.changeCoverImage(this);
    }

    public Long getCapacity() {
        return capacity.getCapacity();
    }

    public String getType() {
        return type.name();
    }

    public Long getWidth() {
        return dimension.getWidth();
    }

    public Long getHeight() {
        return dimension.getHeight();
    }

    public Session getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "id=" + id +
                ", sessionId=" + session.getId() +
                ", capacity=" + capacity.getCapacity() +
                ", type=" + type.name() +
                ", dimension.width=" + dimension.getWidth() +
                ", dimension.height=" + dimension.getHeight() +
                '}';
    }
}
