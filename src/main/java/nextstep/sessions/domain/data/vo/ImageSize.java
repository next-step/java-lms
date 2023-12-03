package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.exception.SessionsException;

public class ImageSize {

    private static final int IMAGE_FILE_SIZE_LIMIT = 1024 * 1024;
    private final int fileSize;
    private final PixelSize pixelSize;

    public ImageSize(int fileSize, int width, int height) {
        validateFileSize(fileSize);
        this.fileSize = fileSize;
        this.pixelSize = new PixelSize(width, height);
    }

    private void validateFileSize(int fileSize) {
        if (!isValidFileSize(fileSize)) {
            throw new SessionsException("이미지 파일 크기가 초과했습니다.");
        }
    }

    private boolean isValidFileSize(int fileSize) {
        return fileSize <= IMAGE_FILE_SIZE_LIMIT;
    }

}
