package nextstep.courses.domain.image;

public class SessionCoverImage {
  private final Long id;
  private final Long sessionId;
  private final SessionImageDimension dimension;
  private final SessionImageExtension extension;
  private final SessionImageCapacity capacity;

  public SessionCoverImage(Long sessionId, int width, int height, String extension, long bytes) {
    this(null, sessionId, new SessionImageDimension(width, height), SessionImageExtension.from(extension), new SessionImageCapacity(bytes));
  }

  public SessionCoverImage(Long id, Long sessionId, SessionImageDimension dimension, SessionImageExtension extension, SessionImageCapacity capacity) {
    this.id = id;
    this.sessionId = sessionId;
    this.dimension = dimension;
    this.extension = extension;
    this.capacity = capacity;
  }

  public Long getId() {
    return id;
  }

  public Long getSessionId() {
    return sessionId;
  }

  public SessionImageDimension getDimension() {
    return dimension;
  }

  public SessionImageExtension getExtension() {
    return extension;
  }

  public SessionImageCapacity getCapacity() {
    return capacity;
  }
}
