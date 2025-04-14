package nextstep.courses.domain;

public class SessionCoverImage {
    public static final SessionCoverImage EMPTY = new SessionCoverImage();

    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final long MAX_IMAGE_SIZE = MB;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    private final String path;
    private final long size;
    private final int width;
    private final int height;
    private final SessionCoverImageExtension extension;

    private SessionCoverImage() {
        this.path = "";
        this.size = 0;
        this.width = 0;
        this.height = 0;
        this.extension = SessionCoverImageExtension.JPG;
    }

    public SessionCoverImage(String path, String extension, long size, int width, int height) {
        this.path = path;
        this.extension = SessionCoverImageExtension.from(extension);
        this.size = size;
        this.width = width;
        this.height = height;

        validateInputs();
    }

    private void validateInputs() {
        validateSize();
        validateWidth();
        validateHeight();
        validateRatio();
    }

    private void validateSize() {
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("1MB 이하만 업로드 가능합니다.");
        }
    }

    private void validateWidth() {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("너비가 300 pixel 이상인 경우만 업로드 가능합니다.");
        }
    }

    private void validateHeight() {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("높이가 200 pixel 이상인 경우만 업로드 가능합니다.");
        }
    }

    private void validateRatio() {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("이미지 비율이 3:2 인 경우만 업로드 가능합니다.");
        }
    }
}
