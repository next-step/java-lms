package nextstep.courses.domain;

public class Image {

    private int size;

    private ImageType type;

    private int width;

    private int height;

    public Image() {
    }

    public Image(int size, ImageType type) {
        if (1 < size) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        this.size = size;
        this.type = type;
    }

    public ImageType getType() {
        return this.type;
    }
}
