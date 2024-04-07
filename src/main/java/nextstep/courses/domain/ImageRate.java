package nextstep.courses.domain;

public class ImageRate {

    public int width;
    public int height;

    public ImageRate(int width, int height) {
        validRate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validRate(int width, int height) {
        if (width / height != 3 / 2) {
            throw new IllegalArgumentException("이미지 width, height 비율이 3:2를 만족하지 않습니다");
        }
    }


}
