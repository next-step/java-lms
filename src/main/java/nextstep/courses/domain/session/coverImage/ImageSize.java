package nextstep.courses.domain.session.coverImage;

public class ImageSize {
    private int size;

    private static final int MAX_SIZE = 1;

    public ImageSize(int size) {
        this.size = size;
    }

    public boolean validSize() {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지는 1MB 이하여야 합니다.");
        }
        return true;
    }
}
