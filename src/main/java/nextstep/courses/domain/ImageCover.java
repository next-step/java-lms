package nextstep.courses.domain;

public class ImageCover {
    public static final int MAX_SIZE = 1;
    public static final String IMAGE_SIZE_ERROR_MESSAGE = "이미지 크기는 %dMB 이하여야 합니다. 현재 사이즈 : %d";
    private double imageSize;
    private ImagePixel imagePixel;
    private ImageType imageType;

    public ImageCover(double imageSize, int width, int height, String imageType) {
        validateSize(imageSize);
        this.imageSize = imageSize;
        this.imagePixel = new ImagePixel(width, height);
        this.imageType = ImageType.from(imageType);
    }

    private void validateSize(final double size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException(String.format(IMAGE_SIZE_ERROR_MESSAGE, MAX_SIZE, size));
        }
    }
}
