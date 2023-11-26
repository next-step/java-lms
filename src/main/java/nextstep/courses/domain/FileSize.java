package nextstep.courses.domain;

import nextstep.courses.exception.VolumeOverException;

public class FileSize {

    private static final long MAX_SIZE = 1024L * 1024L;
    private final long fileSize;

    public FileSize(long fileSize) {
        if (fileSize > MAX_SIZE) {
            throw new VolumeOverException("파일 크기는 1MB 이하여야 합니다.");
        }

        this.fileSize = fileSize;
    }
}
