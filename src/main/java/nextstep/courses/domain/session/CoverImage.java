package nextstep.courses.domain.session;

import nextstep.courses.exception.SessionCoverImageException;
import nextstep.courses.exception.SessionException;

import java.util.ArrayList;
import java.util.List;

public class CoverImage {

    public static final int MAX_FILE_SIZE = 1024 * 1024;
    public static final int WIDTH = 300;
    public static final int HEIGHT = 200;
    public static final List<String> EXTENSIONS = new ArrayList<String>(List.of("gif", "jpg", "jpeg", "png", "svg"));

    private final long size;
    private final int width;
    private final int height;
    private final String extension;

    public CoverImage(long size, int width, int height, String extension) throws SessionException {
        validate(size, width, height, extension);
        this.size = size;
        this.width = width;
        this.height = height;
        this.extension = extension;
    }

    private void validate(long size, int width, int height, String extension) throws SessionException {
        if (!isPossibleFileSize(size)) {
            throw new SessionCoverImageException(size, width, height, extension);
        }

        if (!isPossibleWidthHeightSize(width, height)) {
            throw new SessionCoverImageException(size, width, height, extension);
        }

        if (!isPossibleExtension(extension)) {
            throw new SessionCoverImageException(size, width, height, extension);
        }

    }

    private boolean isPossibleWidthHeightSize(int width, int height) {
        return width >= WIDTH && height >= HEIGHT && width * 2 == height * 3;
    }

    private boolean isPossibleFileSize(long size) {
        return size <= MAX_FILE_SIZE;
    }

    private boolean isPossibleExtension(String extension) {
        return EXTENSIONS.contains(extension);
    }


}
