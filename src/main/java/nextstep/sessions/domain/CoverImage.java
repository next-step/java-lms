package nextstep.sessions.domain;

public class CoverImage {
    private static final long MAX_FILE_SIZE = 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = 3.0 / 2.0;

    private final Long id;
    private final String fileName;
    private final int width;
    private final int height;
    private final long size;
    private final EnableExtension extension;

    public CoverImage(Long id, String fileName, int width, int height, long size) {
        assertImageUnderMaxSize(size);

        final EnableExtension extension = EnableExtension.from(fileName);
        assertValidExtension(extension);

        assertValidWidth(width);

        assertValidHeight(height);

        assertValidRatio(width, height);

        this.id = id;
        this.fileName = fileName;
        this.width = width;
        this.height = height;
        this.size = size;
        this.extension = extension;
    }

    private void assertImageUnderMaxSize(long size) {
        if (MAX_FILE_SIZE < size) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이히여야 합니다.");
        }
    }

    private void assertValidExtension(EnableExtension extension) {
        if (extension == EnableExtension.INVALID) {
            throw new IllegalArgumentException("지원하지 않는 확장자 입니다.");
        }
    }

    private void assertValidWidth(int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지 너비는 " + MIN_WIDTH + "픽셀 이상이어야 합니다.");
        }
    }

    private void assertValidHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지 높이는 " + MIN_HEIGHT + "픽셀 이상이어야 합니다.");
        }
    }

    private void assertValidRatio(int width, int height) {
        if ((double) width != height * RATIO) {
            throw new IllegalArgumentException("이미지 비율이 적합하지 않습니다.");
        }
    }

}
