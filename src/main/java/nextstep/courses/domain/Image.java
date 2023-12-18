package nextstep.courses.domain;

public class Image {
    private final int size;
    public Image(int size) {
        validateSize(size);
        this.size = size;
    }

    private void validateSize(int size) {
        if (size > 1) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }
}
