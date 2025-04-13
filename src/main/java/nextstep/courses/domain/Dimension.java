package nextstep.courses.domain;

public class Dimension {
  private static final int MIN_WIDTH = 300;
  private static final int MIN_HEIGHT = 200;
  private static final int WIDTH_RATIO = 3;
  private static final int HEIGHT_RATIO = 2;

  private final int width;
  private final int height;

  public Dimension(int width, int height) {
    this.width = width;
    this.height = height;
  }

  public boolean isValid() {
    return width >= MIN_WIDTH
        && height >= MIN_HEIGHT
        && width * HEIGHT_RATIO == height * WIDTH_RATIO;
  }
}
