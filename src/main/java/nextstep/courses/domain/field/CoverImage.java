package nextstep.courses.domain.field;

public class CoverImage {

    public static final Long SIZE_LIMIT = 1024L * 1024L;

    public static CoverImage DEFAULT_IMAGE = CoverImage.of(CoverImage.SIZE_LIMIT, 300L, 200L, "jpge");

    private static final double DENOMINATOR = (3d / 2d);

    private long size;

    private long width;

    private long height;

    private ImageType imageType;


    public CoverImage(long size,
                      long width,
                      long height,
                      ImageType imageType) {
        overSize(size);
        checkSizeRatio(width, height);

        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    public static CoverImage of(long size,
                                long width,
                                long height,
                                String imageType) {
        return new CoverImage(size, width, height, ImageType.getType(imageType));
    }

    public ImageType getImageType() {
        return imageType;
    }

    private void overSize(long size) {
        if (SIZE_LIMIT < size) {
            throw new IllegalArgumentException("이미지 크기가 너무 큽니다(1MB 초과)");
        }
    }

    private void checkSizeRatio(double width, double height) {
        if (width / DENOMINATOR != height) {
            throw new IllegalArgumentException("가로와 세로의 비율이 적절하지 않습니다");
        }
    }

    @Override
    public String toString() {
        return "CoverImage{" +
               "size=" + size +
               ", width='" + width + '\'' +
               ", height='" + height + '\'' +
               ", imageType=" + imageType +
               '}';
    }
}
