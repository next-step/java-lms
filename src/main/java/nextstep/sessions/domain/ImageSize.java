package nextstep.sessions.domain;

public class ImageSize {
    /*
    이미지 크기
    이미지 크기, 가로 크기, 세로 크기를 관리한다.
    이미지 크기는 제한이 있으며, 가로 세로 크기와 비율에 대한 제한이 있다.
     */

    private static final int MAX_SIZE = 1_000_000;
    private static final double MIN_WIDTH = 300;
    private static final double MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private int size;

    private double width;

    private double height;

    public ImageSize(int size, double width, double height) {
        if (size <= 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지는 가로 300px, 세로 200px 이상이여야 합니다.");
        }
        if (isNotCorrectRatio(width, height)) {
            throw new IllegalArgumentException("이미지의 비율은 3:2(가로:세로)여야 한다.");
        }
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private boolean isNotCorrectRatio(double width, double height) {
        // 이미지의 비율 3:2를 검증
        return width * HEIGHT_RATIO != height * WIDTH_RATIO;
    }

    public int getSize() {
        return size;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
