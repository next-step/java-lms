package nextstep.courses.domain.session.info.basic;

import nextstep.courses.domain.image.ImageFileName;
import nextstep.courses.domain.image.ImageSize;

public class SessionThumbnail {
    private static final int MAX_FILE_SIZE = 1024 * 1024; // 1MB

    private final long fileSize;
    private final ImageFileName fileName;
    private final ImageSize size;

    public SessionThumbnail(String fullFileName, long fileSize, int width, int height) {
        validateFileSize(fileSize);
        this.fileSize = fileSize;
        this.fileName = new ImageFileName(fullFileName);
        this.size = new ImageSize(width, height);
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("파일 크기는 1MB를 초과할 수 없습니다.");
        }
    }
}
