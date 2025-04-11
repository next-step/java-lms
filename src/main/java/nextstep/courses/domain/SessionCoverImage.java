package nextstep.courses.domain;

public class SessionCoverImage {
    private static final long KB = 1024;
    private static final long MB = KB * KB;
    private static final long MAX_IMAGE_SIZE = 1 * MB;

    public static void validateSize(long input) {
        if (input > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("1MB 이하만 업로드 가능합니다.");
        }
    }
}
