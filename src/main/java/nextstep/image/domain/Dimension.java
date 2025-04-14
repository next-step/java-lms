package nextstep.image.domain;

public class Dimension {
  private static final int MIN_WIDTH = 300;
  private static final int MIN_HEIGHT = 200;
  private static final int WIDTH_RATIO = 3;
  private static final int HEIGHT_RATIO = 2;

  private final int width;
  private final int height;

  public Dimension(int width, int height) {
    validate(width, height);

    this.width = width;
    this.height = height;
  }

  private void validate(int width, int height) {
    if (width < MIN_WIDTH) {
      throw new IllegalArgumentException("너비는 " + MIN_WIDTH + " 이상이어야 합니다.");
    }
    if (height < MIN_HEIGHT) {
      throw new IllegalArgumentException("높이는 " + MIN_HEIGHT + " 이상이어야 합니다.");
    }
    if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
      throw new IllegalArgumentException("비율이 맞지 않습니다. " + WIDTH_RATIO + ":" + HEIGHT_RATIO);
    }
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }
}
