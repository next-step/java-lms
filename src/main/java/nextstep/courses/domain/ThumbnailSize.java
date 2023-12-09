package nextstep.courses.domain;

import nextstep.courses.exception.FileException.FileSizeException;

public class ThumbnailSize {

    private static final long MAX_THUMBNAIL_FILE_SIZE = 1024L * 1024L;
    private static final long MIN_THUMBNAIL_FILE_SIZE = 0L;

    private final long size;

    public ThumbnailSize(long size) {
        validateFileSize(size);
        this.size = size;
    }

    private void validateFileSize(long size) {
        if (size < MIN_THUMBNAIL_FILE_SIZE) {
            throw new FileSizeException("파일 크기는 음수일 수 없습니다.");
        }
        if (size > MAX_THUMBNAIL_FILE_SIZE) {
            throw new FileSizeException("파일 크기는 1MB가 넘으면 안됩니다.");
        }
    }
}
