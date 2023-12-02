package nextstep.courses.domain.image;

public class ImageSize {

    private static final int MAX_SIZE = 1024 * 1024;

    private final int value;

    public ImageSize(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        if (value <= 0 || value > MAX_SIZE) {
            throw new IllegalArgumentException("파일용량을 확인하세요.");
        }
    }

    public int value() {
        return value;
    }
}
