package nextstep.courses.domain;

public class Image {

    final static int MAX_SIZE = 1024;
    final static int MIN_WIDTH = 300;
    final static int MIN_HEIGHT = 200;
    final static double WIDTH_HEIGHT_RATIO = 3 / 2;

    int size;
    int width;
    int height;
    ImageType type;

    public Image(int size, int width, int height, ImageType type) {
        validateSize(size, width, height);
        this.size = size;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    private void validateSize(int size, int width, int height) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB보다 작아야 합니다.");
        }

        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 크기는 300x200보다 커야 합니다.");
        }

        if (width / height != WIDTH_HEIGHT_RATIO) {
            throw new IllegalArgumentException("이미지의 가로 세로 비율은 3:2여야 합니다.");
        }
    }
}
