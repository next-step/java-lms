package nextstep.courses.domain;

public class ImageSize {

    private final int MAX_SIZE = 1;

    private int size;

    public ImageSize(int size) {
        validSize(size);
        this.size = size;
    }

    private void validSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 사이즈가 1MB를 초과했습니다: " + size + "MB");
        }
    }
}
