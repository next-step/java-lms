package nextstep.courses.domain.image;

import nextstep.courses.domain.image.constant.ImageType;

public class CoverImage {

    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    public static final double ASPECT_RATIO = 1.5;
    public static final double MAX_FILE_SIZE = 1024 * 1024; // 1MB

    private final long size;
    private final ImageType type;
    private final int width;
    private final int height;

    public CoverImage(long size, String type, int width, int height) {
        this(size, ImageType.fromName(type.toUpperCase()), width, height);
    }

    public CoverImage(long size, ImageType type, int width, int height) {
        validateSize(width, height);
        validateRatio(width, height);
        validateFileSize(size);
        this.size = size;
        this.type = type;
        this.width = width;
        this.height = height;
    }

    private void validateFileSize(long size) {
        if (size > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("이미지 사이즈는 1MB 이하만 가능합니다.");
        }
    }

    private void validateRatio(int width, int height) {
        double ratio = (double) width / height;
        if (ratio != ASPECT_RATIO) {
            throw new IllegalArgumentException("3:2 비율로 다시 업로드 해주세요.");
        }
    }

    private void validateSize(int width, int height) {
        if (isSize(width, height)) {
            throw new IllegalArgumentException("너비는 300px, 높이는 200px 이상이어야 합니다.");
        }
    }

    private static boolean isSize(int width, int height) {
        return width < MIN_WIDTH || height < MIN_HEIGHT;
    }

    public long getSize() {
        return size;
    }

    public ImageType getType() {
        return type;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
