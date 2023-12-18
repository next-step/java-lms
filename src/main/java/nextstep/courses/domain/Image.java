package nextstep.courses.domain;

public class Image {
    private static final int MAX_SIZE = 1;
    private final int size;
    private final ImageType imageType;
    public Image(int size, String type) {
        validateSize(size);
        this.size = size;
        this.imageType = ImageType.of(type);
    }

    private void validateSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }
}
