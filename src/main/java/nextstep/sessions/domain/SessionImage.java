package nextstep.sessions.domain;

public class SessionImage {
    /*
    강의 커버 이미지
    이미지 크기, 가로 크기, 세로 크기, 타입을 관리한다.
    이미지는 크기와 타입의 제한이 있으며, 가로 세로 크기와 비율에 대한 제한이 있다.
     */

    private static final int MAX_SIZE = 1_000_000;
    private static final double MIN_WIDTH = 300;
    private static final double MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private Long id;

    private int size;

    private double width;

    private double height;

    private ImageType type;

    public SessionImage(int size, double width, double height, String type) {
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
        this.type = ImageType.from(type);
    }

    public SessionImage(Long id, int size, double width, double height, ImageType type) {
        this.id = id;
        this.size = size;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    private boolean isNotCorrectRatio(double width, double height) {
        // 이미지의 비율 3:2를 검증
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            return true;
        }
        return false;
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

    public ImageType getType() {
        return type;
    }
}
