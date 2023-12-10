package nextstep.courses.domain.coverimage;

import java.util.List;

public class CoverImage {

    private static final List<String> AVAILABLE_IMAGE_TYPE = List.of("gif", "jpg", "jpeg", "png", "svg");
    private static final int MAX_CAPACITY = 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double FIXED_IMAGE_RATIO = 3.0 / 2.0;

    private final String imageType;
    private final int imageCapacity;
    private final int width;
    private final int height;


    public CoverImage(String imageType, int imageCapacity, int width, int height) {
        validateImageType(imageType);
        validateImageCapacity(imageCapacity);
        validateWidth(width);
        validateHeight(height);
        validateImageRatio(width, height);

        this.imageType = imageType;
        this.imageCapacity = imageCapacity;
        this.width = width;
        this.height = height;
    }

    private void validateImageType(String imageType) {
        if (!AVAILABLE_IMAGE_TYPE.contains(imageType)) {
            throw new IllegalArgumentException("강의 커버 이미지는 " + AVAILABLE_IMAGE_TYPE + " 타입만 가능합니다.");
        }
    }

    private void validateImageCapacity(int imageCapacity) {
        if (imageCapacity > MAX_CAPACITY) {
            throw new IllegalArgumentException("강의 커버 이미지는 " + MAX_CAPACITY + " 이하여야 합니다.");
        }
    }

    private void validateWidth(int width) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지의 width는 " + MIN_WIDTH + " 픽셀 이상이어야 합니다.");
        }
    }

    private void validateHeight(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지의 height는 " + MIN_HEIGHT + " 이상이어야 합니다.");
        }
    }

    private void validateImageRatio(double width, double height) {
        double ratio = Math.round(width / height * 10.0) / 10.0;
        if (ratio != FIXED_IMAGE_RATIO) {
            throw new IllegalArgumentException("이미지의 해상도는 " + FIXED_IMAGE_RATIO + " 이어야 합니다.");
        }
    }
}
