package nextstep.courses.domain.image;

public class SessionCoverImage {
  private final SessionImageDimension dimension;
  private final SessionImageExtension extension;
  private final SessionImageCapacity capacity;

  public SessionCoverImage(int width, int height, String extension, long bytes) {
    this(new SessionImageDimension(width, height), SessionImageExtension.from(extension), new SessionImageCapacity(bytes));
  }

  public SessionCoverImage(SessionImageDimension dimension, SessionImageExtension extension, SessionImageCapacity capacity) {
    this.dimension = dimension;
    this.extension = extension;
    this.capacity = capacity;
  }
}
