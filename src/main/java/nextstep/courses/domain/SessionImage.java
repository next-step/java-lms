package nextstep.courses.domain;

public class SessionImage {

    private static final int ONE_MEGA_BYTE = 1_000_000;

    private Integer capacity;
    private ImageExtension extension;
    private SessionImageSize size;

    public SessionImage(int capacity, ImageExtension extension, int width, int height) {
        validate(capacity);
        this.capacity = capacity;
        this.extension = extension;
        this.size = new SessionImageSize(width, height);
    }

    private void validate(int capacity) {
        capacityValidate(capacity);
    }

    private void capacityValidate(int capacity) {
        if (ONE_MEGA_BYTE < capacity) {
            throw new IllegalArgumentException(" 이미지 용량은 1MB 이하만 가능합니다. ");
        }
    }

}
