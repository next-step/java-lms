package nextstep.courses.domain;

import nextstep.courses.exception.SessionImageException;

import java.util.List;

public class SessionImage {

    private static final int MAX_SIZE_BYTE = 1000;
    private static final int MIN_WIDTH_SIZE_PIXEL = 300;
    private static final int MIN_HEIGHT_SIZE_PIXEL = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;
    private static final List<String> EXTENSIONS = List.of("gif", "jpg", "jpeg", "png", "svg");

    private final int size;
    private final String extension;
    private final int width;
    private final int height;

    public SessionImage(int size, String extension, int width, int height) {
        validateSize(size);
        validateExtension(extension);
        validateLength(width, height);
        validateLengthRatio(width, height);
        this.size = size;
        this.extension = extension;
        this.width = width;
        this.height = height;
    }

    private void validateSize(int size) {
        if (size > MAX_SIZE_BYTE) {
            throw new SessionImageException("강의 커버 이미지 사이즈는 1MB 이하여야 합니다.");
        }
    }

    private void validateExtension(String extension) {
        if (!EXTENSIONS.contains(extension)) {
            throw new SessionImageException("강의 커버 이미지 확장자는 gif, jpg(jpeg 포함), png, svg만 허용됩니다.");
        }
    }

    private void validateLength(int width, int height) {
        if (isWidthLessThanMinimum(width) || isHeightLessThanMinimum(height)) {
            throw new SessionImageException("이미지의 width 300픽셀, height는 200픽셀 이상이여야 합니다.");
        }
    }

    private boolean isWidthLessThanMinimum(int width) {
        return MIN_WIDTH_SIZE_PIXEL > width;
    }

    private boolean isHeightLessThanMinimum(int height) {
        return MIN_HEIGHT_SIZE_PIXEL > height;
    }

    private void validateLengthRatio(int width, int height) {
        if(!isRatioThreeToTwo(width, height)){
            throw new SessionImageException("이미지의 width와 height의 비율은 3:2여야 합니다.");
        }
    }

    private boolean isRatioThreeToTwo(int width, int height) {
        return (HEIGHT_RATIO * width) == (WIDTH_RATIO * height);
    }

}
