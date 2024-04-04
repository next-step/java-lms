package nextstep.courses.domain.vo;

public class Capacity {
    private static final int IAMGE_SIZE_THRESHOLD = 1;

    private final int imageSize;

    public Capacity(int imageSize) {
        if (!satisfy(imageSize)) {
            throw new IllegalArgumentException("capacity must under 1mb");
        }
        this.imageSize = imageSize;
    }

    private boolean satisfy(int imageSize) {
        return imageSize <= IAMGE_SIZE_THRESHOLD;
    }
}
