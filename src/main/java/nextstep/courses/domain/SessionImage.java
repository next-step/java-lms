package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;

public class SessionImage {
    private static final long MAX_FILE_SIZE = 1024 * 1024;
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final int WIDTH_RATIO = 3;
    private static final int HEIGHT_RATIO = 2;
    private static final List<String> ALLOWED_TYPES = Arrays.asList("gif", "jpg", "jpeg", "png", "svg");

    private final long fileSize;
    private final String imageType;
    private final int width;
    private final int height;

    public SessionImage(long fileSize, String imageType, int width, int height) {
        validateFileSize(fileSize);
        validateImageType(imageType);
        validateDimensions(width, height);
        validateRatio(width, height);

        this.fileSize = fileSize;
        this.imageType = imageType;
        this.width = width;
        this.height = height;
    }

    private void validateFileSize(long fileSize) {
        if (fileSize > MAX_FILE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 한다");
        }
    }

    private void validateImageType(String imageType) {
        if (!ALLOWED_TYPES.contains(imageType.toLowerCase())) {
            throw new IllegalArgumentException("지원하지 않는 이미지 타입: " + imageType);
        }
    }

    private void validateDimensions(int width, int height) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("width는 300픽셀 이상이어야 한다.");
        }
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("height는 200픽셀 이상이어야 한다.");
        }
    }

    private void validateRatio(int width, int height) {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new IllegalArgumentException("이미지 비율은 3:2여야 합니다.");
        }
    }


}
