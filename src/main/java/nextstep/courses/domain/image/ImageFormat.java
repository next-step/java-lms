package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFormatException;

public class ImageFormat {

    public static int LIMIT_IMAGE_SIZE = 1024 * 1024; // 1MB
    public static int MIN_IMAGE_WIDTH_IN_PIXELS = 300;
    public static int MIN_IMAGE_HEIGHT_IN_PIXELS = 200;
    public static double ASPECT_RATIO = (double) MIN_IMAGE_WIDTH_IN_PIXELS / MIN_IMAGE_HEIGHT_IN_PIXELS;


    private int imageSize;
    private int width;
    private int height;
    private ImageType imageType;

    public ImageFormat(int imageSize, int width, int height, ImageType imageType) {
        validateImageFormat(imageSize, width, height);

        this.imageSize = imageSize;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void validateImageFormat(int size, int width, int height) throws InvalidImageFormatException {
        validateImageSize(size);
        validateWidthHeight(width, height);
    }

    private void validateWidthHeight(int width, int height) throws InvalidImageFormatException {
        if (width < MIN_IMAGE_WIDTH_IN_PIXELS || width <= 0) {
            throw new InvalidImageFormatException("이미지 가로는 300픽셀 이상이어야 합니다.");
        }
        if (height < MIN_IMAGE_HEIGHT_IN_PIXELS || height <= 0) {
            throw new InvalidImageFormatException("이미지 세로는 200픽셀 이상이어야 합니다.");
        }
        if ((double) width / height != ASPECT_RATIO) {
            throw new InvalidImageFormatException("이미지 가로 세로 비율은 3:2여야 합니다.");
        }
    }

    private void validateImageSize(int imageSize) throws InvalidImageFormatException {
        if (imageSize > LIMIT_IMAGE_SIZE) {
            throw new InvalidImageFormatException("이미지 크기는 1MB 이하만 가능합니다.");
        }
        if (imageSize <= 0) {
            throw new InvalidImageFormatException("이미지 크기는 0MB 이하로는 불가능합니다.");
        }
    }

    public int getImageSize() {
        return imageSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ImageType getImageType() {
        return imageType;
    }
}
