package nextstep.courses.domain.image;

public class CoverImage {
    private final long size;
    private final ImageType type;
    private final int width;
    private final int height;

    public CoverImage(long size, ImageType type, int width, int height) {
        validateSize(size);
        validateDimension(width, height);
        validateRatio(width, height);
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validateRatio(int width, int height) {
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDimension(int width, int height) {
        if (width < 300 || height < 200) {
            throw new IllegalArgumentException();
        }
    }

    private void validateSize(long size) {
        if (size > 1_048_576L) {
            throw new IllegalArgumentException();
        }
    }
}
