package nextstep.courses.domain;

public class ImageSize {
  private static final Long WIDTH_MAX_SIZE = 300L;
  private static final Long HEIGHT_MAX_SIZE = 200L;
  private final double width;
  private final double height;

  public ImageSize(double width, double height) {
    if (width < WIDTH_MAX_SIZE) {
      throw new IllegalArgumentException("width가 작습니다.");
    }
    if (height < HEIGHT_MAX_SIZE) {
      throw new IllegalArgumentException("height이 작습니다.");
    }
    if (Double.compare(width/3, height/2) != 0) {
      throw new IllegalArgumentException("width와 height의 비율이 맞지 않습니다.");
    }
    this.width = width;
    this.height = height;
  }
}
