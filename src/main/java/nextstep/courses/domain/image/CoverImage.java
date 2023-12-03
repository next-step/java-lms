package nextstep.courses.domain.image;

public class CoverImage {
    private static final Long MAX_SIZE = 1024L * 1024L;
    public static final String MAX_SIZE_OVER_MSG = "이미지 사이즈는 1MB를 초과 할 수 없습니다.";
    private final long size;
    private final ImagePixel imagePixel;
    private final ImageType imageType;

    public CoverImage(final long size, final ImagePixel imagePixel, final ImageType imageType) {
        checkSize(size);

        this.size = size;
        this.imagePixel = imagePixel;
        this.imageType = imageType;
    }

    private static void checkSize(final long size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException(MAX_SIZE_OVER_MSG);
        }
    }
}
