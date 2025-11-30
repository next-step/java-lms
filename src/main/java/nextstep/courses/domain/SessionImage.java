package nextstep.courses.domain;

import java.util.Arrays;
import java.util.List;

public class SessionImage {
    private static final long MAX_FILE_SIZE = 1024 * 1024;
    private static final List<String> ALLOWED_TYPES = Arrays.asList("gif", "jpg", "jpeg", "png", "svg");

    private final long fileSize;
    private final String imageType;
    private final ImageDimension imageDimension;

    public SessionImage(long fileSize, String imageType, int width, int height) {
        this(fileSize, imageType, new ImageDimension(width, height));
    }

    public SessionImage(long fileSize, String imageType, ImageDimension imageDimension) {
        validateFileSize(fileSize);
        validateImageType(imageType);
        this.fileSize = fileSize;
        this.imageType = imageType;
        this.imageDimension = imageDimension;
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

}
