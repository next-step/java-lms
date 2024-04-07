package nextstep.courses.domain.session;

public class CoverImageDetailInfo {

  public static final int ONE_MB = 1024 * 1024;
  public static final int MIN_COVER_IMAGE_WIDTH = 300;
  public static final int MIN_COVER_IMAGE_HEIGHT = 200;
  private static final double IMAGE_COVER_RATIO = 3.0 / 2.0;
  public static final String EXCEED_MAX_COVER_IMAGE_SIZE = "최대 이미지 커버 사이즈를 넘겼습니다. input: %s";
  public static final String INVALID_COVER_IMAGE_WIDTH = "올바르지 않은 이미지 커버 width 입니다. input: %s";
  public static final String INVALID_COVER_IMAGE_HEIGHT = "올바르지 않은 이미지 커버 height 입니다. input: %s";
  public static final String INVALID_COVER_IMAGE_RATIO = "올바르지 않은 width와 height의 비율입니다. widthInput: %s, heightInput: %s";

  private int imageSize;
  private int imageWidth;
  private int imageHeight;

  private CoverImageDetailInfo() {
  }

  public CoverImageDetailInfo(int imageSize, int imageWidth, int imageHeight) {
    validate(imageSize, imageWidth, imageHeight);
    this.imageSize = imageSize;
    this.imageWidth = imageWidth;
    this.imageHeight = imageHeight;
  }

  public int getImageSize() {
    return imageSize;
  }

  public int getImageWidth() {
    return imageWidth;
  }

  public int getImageHeight() {
    return imageHeight;
  }

  private boolean validate(int imageSize, int imageWidth, int imageHeight) {

    if (ONE_MB < imageSize) {
      throw new IllegalArgumentException(String.format(EXCEED_MAX_COVER_IMAGE_SIZE, imageSize));
    }

    if (imageWidth < MIN_COVER_IMAGE_WIDTH) {
      throw new IllegalArgumentException(String.format(INVALID_COVER_IMAGE_WIDTH, imageWidth));
    }

    if (imageHeight < MIN_COVER_IMAGE_HEIGHT) {
      throw new IllegalArgumentException(String.format(INVALID_COVER_IMAGE_HEIGHT, imageHeight));
    }

    double ratio = (double) imageWidth / imageHeight;
    if (ratio != IMAGE_COVER_RATIO) {
      throw new IllegalArgumentException(String.format(INVALID_COVER_IMAGE_RATIO, imageWidth, imageHeight));
    }

    return true;
  }
}
