package nextstep.courses.domain.image;

import nextstep.courses.exception.InvalidImageException;

public class Image {

    private static final int ONE_MB = 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;

    private int size;
    private int width;
    private int height;
    private ImageType imageType;

    public Image(int size, ImageType imageType, int width, int height) throws InvalidImageException {
        validateImageSize(size);
        validateImageWidthAndHeight(width, height);
        validateImageAspectRatio(width, height);
        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private static void validateImageAspectRatio(double width, double height)
        throws InvalidImageException {
        if (Math.round(width / height * 10.0) / 10.0 != 1.5) {
            throw new InvalidImageException("width와 height의 비율은 3:2여야 한다.");
        }
    }

    private static void validateImageWidthAndHeight(int width, int height)
        throws InvalidImageException {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new InvalidImageException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다.");
        }
    }

    private static void validateImageSize(int imageSize) throws InvalidImageException {
        if (imageSize > ONE_MB) {
            throw new InvalidImageException("이미지 크기는 1MB 이하여야 한다.");
        }
    }

}
