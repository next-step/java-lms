package nextstep.courses.domain;

public class SessionImage {
    private static final long MAX_IMGE_SIZE = 1L * 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;
    private final Long id;
    private final ImageType type;
    private final long size;
    private final int width;
    private final int height;

    public SessionImage(Long id, String type, long size, int width, int height) {
        this.id = id;
        this.type = ImageType.of(type);
        this.size = size;
        this.width = width;
        this.height = height;
        validateSize();
        validateMinPixel();
        validateRatio();
    }

    private void validateRatio() {
        if (!isCorrectRatio()) {
            throw new IllegalArgumentException("width와 height의 비율은 3:2여야 한다.");
        }
    }

    private boolean isCorrectRatio() {
        return WIDTH_RATIO * height == HEIGHT_RATIO * width;
    }

    private void validateMinPixel() {
        if(width < MIN_WIDTH || height < MIN_HEIGHT){
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다.");
        }
    }

    private void validateSize() {
        if(this.size > MAX_IMGE_SIZE){
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }
}
