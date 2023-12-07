package nextstep.courses.domain;

public class ImageFileSize {
  private static final Long FILE_MAX_SIZE = 1024L*1024L;
  private final long size;

  public ImageFileSize(long size) {
    if (size >= FILE_MAX_SIZE) {
      throw new IllegalArgumentException("사이즈가 너무 큽니다.");
    }
    this.size = size;
  }
}
