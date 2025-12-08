package nextstep.courses.domain.image;

public class SessionImageDimension {
  private static final int MIN_WIDTH = 300;
  private static final int MIN_HEIGHT = 200;
  private static final int RATIO_W = 3;
  private static final int RATIO_H = 2;

  private int width;
  private int height;

  public SessionImageDimension(int width, int height) {
    validateMinLength(width, height);
    validateRatio(width, height);
    this.width = width;
    this.height = height;
  }

  private void validateMinLength(int width, int height){
    if(!(width >= MIN_WIDTH && height >= MIN_HEIGHT)) {
      throw new IllegalArgumentException("이미지는 가로 300이상, 세로 200 이상이어야 합니다.");
    }
  }

  private void validateRatio(int width, int height){
    int gcd = gcd(width, height);
    int ratioW = width / gcd;
    int ratioH = height / gcd;
    if(!(ratioW == RATIO_W && ratioH == RATIO_H)){
      throw new IllegalArgumentException("이미지는 가로 x 세로 3대 2이어야 합니다.");
    }
  }

  private int gcd(int a, int b) {
    return b == 0 ? a : gcd(b, a % b);
  }

  public int width() {
    return width;
  }

  public int height() {
    return height;
  }
}
