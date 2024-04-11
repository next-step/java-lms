package nextstep.sessions.domain;

public class FileSize {
    private static final long MAX_FILE_SIZE = 1024 * 1024;

    private final long size;

    public FileSize(long size) {
        assertUnderMaxSize(size);

        this.size = size;
    }

    private void assertUnderMaxSize(long size) {
        if (MAX_FILE_SIZE < size) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이히여야 합니다.");
        }
    }


}
