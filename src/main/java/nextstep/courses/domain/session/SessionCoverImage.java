package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionCoverImageException;
import nextstep.courses.exception.SessionException;

public class SessionCoverImage {

    public static final int MAX_FILE_SIZE = 1024 * 1024;
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;

    private final long size;
    private final int width;
    private final int height;
    private final String extension;

    public SessionCoverImage(long size, int width, int height, String extension) throws SessionException {
        validate(size, width, height, extension);
        this.size = size;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    private void validate(long size, int width, int height, String extension) {
        if (!isPossibleFileSize(size)
            || !isPossibleWidthHeightSize(width, height)
            || !isPossibleWidthHeightRatio(width, height)
            || !ImageExtension.isPossibleExtension(extension)) {

            throw new SessionCoverImageException(size, width, height, extension);
        }
    }

    private boolean isPossibleWidthHeightSize(int width, int height) {
        return width >= MIN_WIDTH && height >= MIN_HEIGHT;
    }

    private boolean isPossibleWidthHeightRatio(int width, int height) {
        return width * 2 == height * 3;
    }

    private boolean isPossibleFileSize(long size) {
        return size <= MAX_FILE_SIZE;
    }

}
