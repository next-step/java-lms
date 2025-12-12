package nextstep.sessions.domain;

public class SessionImage {

    private static final long MAX_SIZE = 1_000_000; // 1MB
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double RATIO = 3.0 / 2.0;

    private final String fileName;
    private final long size;
    private final int width;
    private final int height;
    private final ImageType type;

    public SessionImage(String fileName, long size, int width, int height) {
        validateFileName(fileName);
        validateSize(size);
        validateDimension(width, height);
        validateRatio(width, height);

        this.fileName = fileName;
        this.size = size;
        this.width = width;
        this.height = height;
        this.type = extractType(fileName);
    }

    public String fileName() {
        return fileName;
    }

    public long size() {
        return size;
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("파일명은 빈 값일 수 없습니다");
        }
    }

    private void validateSize(long size) {
        if (size <= 0 || size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 용량은 1MB 이하여야 합니다");
        }
    }

    private void validateDimension(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지 크기가 최소 조건을 만족하지 않습니다");
        }
    }

    private void validateRatio(int width, int height) {
        double ratio = (double) width / height;
        if (Math.abs(ratio - RATIO) > 0.0001) {
            throw new IllegalArgumentException("이미지 비율은 3:2여야 합니다");
        }
    }

    private ImageType extractType(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf('.') + 1);
        return ImageType.from(ext);
    }

}
