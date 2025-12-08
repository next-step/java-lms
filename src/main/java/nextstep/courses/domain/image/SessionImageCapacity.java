package nextstep.courses.domain.image;

public class SessionImageCapacity {
  private static final long MAX_CAPACITY_BYTES = 1024 * 1024; // 1MB
  private final long bytes;

  public SessionImageCapacity(long bytes) {
    validate(bytes);
    this.bytes = bytes;
  }

  public static SessionImageCapacity ofKB(long kb) {
    return new SessionImageCapacity(kb * 1024);
  }

  public static SessionImageCapacity ofMB(long mb) {
    return new SessionImageCapacity(mb * 1024 * 1024);
  }

  private void validate(long bytes) {
    if (bytes <= 0) {
      throw new IllegalArgumentException("이미지 크기는 0보다 커야 합니다.");
    }
    if (bytes > MAX_CAPACITY_BYTES) {
      throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
    }
  }

  public long bytes() {
    return bytes;
  }
}
