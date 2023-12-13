package nextstep.courses.domain.coverimage;

public class ImageSize {
  private static final Long WIDTH_MAX_SIZE = 300L;
  private static final Long HEIGHT_MAX_SIZE = 200L;
  private final long width;
  private final long height;

  public ImageSize(long width, long height) {
    if (width < WIDTH_MAX_SIZE) {
      throw new IllegalArgumentException(String.format("width는 %s보다 커야합니다.", WIDTH_MAX_SIZE));
    }
    if (height < HEIGHT_MAX_SIZE) {
      throw new IllegalArgumentException(String.format("height은 %s보다 커야합니다.", HEIGHT_MAX_SIZE));
    }
    if (width * 2 != height * 3 ) {
      throw new IllegalArgumentException("width와 height의 비율은 3:2 입니다.");
    }
    this.width = width;
    this.height = height;
  }

  public double getWidth() {
    return width;
  }

  public double getHeight() {
    return height;
  }
}
