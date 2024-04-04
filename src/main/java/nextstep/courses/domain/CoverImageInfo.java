package nextstep.courses.domain;

import nextstep.courses.domain.enums.ImageType;

import java.util.Optional;

public class CoverImageInfo {
    private static final String IMAGE_SIZE_OVER_MESSAGE = "이미지 크기는 1024KB(1MB) 이하여야 합니다.";
    private static final String IMAGE_TYPE_INVALID_MESSAGE = "이미지 타입은 gif, jpg(jpeg 포함), png, svg만 가능합니다.";
    private static final String IMAGE_WIDTH_UNDER_MESSAGE = "이미지의 너비는 300 픽셀 이상이어야 합니다.";
    private static final String IMAGE_HEIGHT_UNDER_MESSAGE = "이미지의 높이는 200 픽셀 이상이어야 합니다.";
    private static final String IMAGE_WRONG_WIDTH_HEIGHT_RATE_MESSAGE = "이미지의 너비와 높이는 3:2 비율이어야 합니다.";

    private static final int MAX_IMAGE_SIZE = 1024;
    private static final int MIN_IMAGE_WIDTH = 300;
    private static final int MIN_IMAGE_HEIGHT = 200;
    private static final int WIDTH_RATE = 3;
    private static final int HEIGHT_RATE = 2;

    private final int size;
    private final ImageType imageType;
    private final int width;
    private final int height;

    public CoverImageInfo(int size, String imageTypeStr, int width, int height) {
        validateImageSize(size);
        validateWidthAndHeight(width, height);
        this.imageType = validatedImageType(imageTypeStr);
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private static void validateImageSize(int size) {
        if (size > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException(IMAGE_SIZE_OVER_MESSAGE);
        }
    }

    private ImageType validatedImageType(String imageTypeStr) {
        Optional<ImageType> imageType = ImageType.findByType(imageTypeStr);
        return imageType.orElseThrow(() -> new IllegalArgumentException(IMAGE_TYPE_INVALID_MESSAGE));
    }

    private void validateWidthAndHeight(int width, int height) {
        if (width < MIN_IMAGE_WIDTH) {
            throw new IllegalArgumentException(IMAGE_WIDTH_UNDER_MESSAGE);
        }

        if (height < MIN_IMAGE_HEIGHT) {
            throw new IllegalArgumentException(IMAGE_HEIGHT_UNDER_MESSAGE);
        }

        if (WIDTH_RATE * height != HEIGHT_RATE * width) {
            throw new IllegalArgumentException(IMAGE_WRONG_WIDTH_HEIGHT_RATE_MESSAGE);
        }
    }
}
