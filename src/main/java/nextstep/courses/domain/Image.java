package nextstep.courses.domain;

import nextstep.courses.ImageException;

import java.util.List;

public class Image {
    private static final int MAX_SIZE = 1_000_000;
    private static final int MAX_WIDTH_SIZE = 300;
    private static final List<String> IMAGE_TYPE_LIST = List.of("gif", "jpg", "jpeg", "png", "svg");

    private final long size;
    private final int width;
    private final int height;
    private final ImageType imageType;

    private Image(long size, int width, int height, ImageType imageType) {
        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    public static Image createImage(long size, int widthSize, int heightSize, String imageType) throws ImageException {
        checkImageSize(size);
        checkImageWidthSize(widthSize);
        checkImageRatio(widthSize, heightSize);

        return new Image(size, widthSize, heightSize, ImageType.of(imageType));
    }

    private static void checkImageSize(long size) throws ImageException {
        if (size >= MAX_SIZE) {
            throw new ImageException("이미지 크기는 1MB 이하여야 한다.");
        }
    }

    private static void checkImageWidthSize(int widthSize) throws ImageException {
        if (widthSize > MAX_WIDTH_SIZE) {
            throw new ImageException("이미지의 width는 300픽셀, height는 200픽셀 이상이어야 한다.");
        }
    }

    private static void checkImageRatio(int widthSize, int heightSize) throws ImageException {
        if (heightSize * 1.5 != widthSize) {
            throw new ImageException("이미지의 width와 height의 비율은 3:2여야 한다.");
        }
    }
}
