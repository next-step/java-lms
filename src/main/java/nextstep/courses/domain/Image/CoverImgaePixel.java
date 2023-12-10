package nextstep.courses.domain.Image;

public class CoverImgaePixel {

    public static final int MIN_WIDTH_SIZE = 300;
    public static final int MIN_HEIGHT_SIZE = 200;
    public static final double WIDTH_HEIGHT_RATIO = 1.5;

    private int width;

    private int height;

    public CoverImgaePixel(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH_SIZE) {
            throw new IllegalArgumentException("너비 픽셀은 300픽셀 이상이어야 합니다");
        }
        if (height < MIN_HEIGHT_SIZE) {
            throw new IllegalArgumentException("높이 픽셀은 200픽셀 이상이어야 합니다");
        }
        if ((double) width / height != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("너비와 높이의 비율은 3:2이어야 합니다");
        }
    }
}
