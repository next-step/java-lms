package nextstep.courses.domain.coverimage;

public class ImageSize {
  private static final Long WIDTH_MAX_SIZE = 300L;
  private static final Long HEIGHT_MAX_SIZE = 200L;
  private final long width;
  private final long height;

  public ImageSize(long width, long height) {
    if (width < WIDTH_MAX_SIZE) {
      throw new IllegalArgumentException("width가 작습니다.");
    }
    if (height < HEIGHT_MAX_SIZE) {
      throw new IllegalArgumentException("height이 작습니다.");
    }
    if (width * 2 != height * 3 ) {
      throw new IllegalArgumentException("width와 height의 비율이 맞지 않습니다.");
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
