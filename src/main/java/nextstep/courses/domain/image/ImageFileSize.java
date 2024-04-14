package nextstep.courses.domain.image;

import nextstep.courses.exception.SessionCoverImageException;

public class ImageFileSize {

    public static final int MAX_FILE_SIZE = 1024 * 1024;

    private final long size;

    public ImageFileSize(long size) {
        validate(size);
        this.size = size;
    }

    public void validate(long size) {
        if (size > MAX_FILE_SIZE) {
            throw new SessionCoverImageException(size);
        }
    }

    public long get() {
        return size;
    }

}
