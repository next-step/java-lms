package nextstep.courses.infrastructure.entity;

public class SessionCoverImageEntity {
    private final Long id;
    private final Long sessionId;
    private final int width;
    private final int height;
    private final String extension;
    private final long capacity;

    public SessionCoverImageEntity(Long id, Long sessionId, int width, int height, String extension, long capacity) {
        this.id = id;
        this.sessionId = sessionId;
        this.width = width;
        this.height = height;
        this.extension = extension;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getExtension() {
        return extension;
    }

    public long getCapacity() {
        return capacity;
    }
}