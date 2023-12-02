package nextstep.courses.domain;

import nextstep.courses.exception.FileException;

public class FileSize {

    private static final long MAX_THUMBNAIL_FILE_SIZE = 1024L * 1024L;

    private final long size;

    public FileSize(long size) {
        validateFileSize(size);
        this.size = size;
    }

    private void validateFileSize(long size) {
        if (size > MAX_THUMBNAIL_FILE_SIZE) {
            throw new FileException("파일 크기는 1MB가 넘으면 안됩니다.");
        }
    }
}
