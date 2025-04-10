package nextstep.courses.domain;

public class SessionThumbnail {
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int ASPECT_RATIO_WIDTH = 3;
    private static final int ASPECT_RATIO_HEIGHT = 2;

    private final String fileName;
    private final long fileSize;
    private final ImageExtension extension;
    private final int width;
    private final int height;

    public SessionThumbnail(String fullFileName, long fileSize, int width, int height) {
        validateFileSize(fileSize);
        
        String[] fileNameParts = fullFileName.split("\\.");
        if (fileNameParts.length != 2) {
            throw new IllegalArgumentException("올바른 파일명 형식이 아닙니다.");
        }
        
        this.fileName = fileNameParts[0];
        this.extension = ImageExtension.from(fileNameParts[1].toLowerCase());
        validateDimensions(width, height);
        
        this.fileSize = fileSize;
        this.width = width;
        this.height = height;
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기는 1MB를 초과할 수 없습니다.");
        }
    }

    private void validateDimensions(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지는 최소 300x200 픽셀 이상이어야 합니다.");
        }
        
        if (width * ASPECT_RATIO_HEIGHT != height * ASPECT_RATIO_WIDTH) {
            throw new IllegalArgumentException("이미지는 3:2 비율이어야 합니다.");
        }
    }
}
