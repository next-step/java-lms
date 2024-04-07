package nextstep.courses.domain;

public class ImageRate {

    private final int MIN_WIDTH_SIZE = 300;
    public int width;
    public int height;

    public ImageRate(int width, int height) {
        validRate(width, height);
        validPixelSize(width);
        this.width = width;
        this.height = height;
    }

    private void validRate(int width, int height) {
        if (width / height != 3 / 2) {
            throw new IllegalArgumentException("이미지 width, height 비율이 3:2를 만족하지 않습니다");
        }
    }

    private void validPixelSize(int width) {
        if (width < MIN_WIDTH_SIZE) {
            throw new IllegalArgumentException("이미지 width가 300px 보다 작습니다");
        }
    }



}
