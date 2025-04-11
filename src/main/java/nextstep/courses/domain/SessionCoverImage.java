package nextstep.courses.domain;

public class SessionCoverImage {
    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final long MAX_IMAGE_SIZE = 1 * MB;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;

    public static void validateSize(long size) {
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("1MB 이하만 업로드 가능합니다.");
        }
    }

    public static void validateWidth(int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("너비가 300 pixel 이상인 경우만 업로드 가능합니다.");
        }
    }

    public static void validateHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("높이가 200 pixel 이상인 경우만 업로드 가능합니다.");
        }
    }

    public static void validateRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("이미지 비율이 3:2 인 경우만 업로드 가능합니다.");
        }
    }
}
