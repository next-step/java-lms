package nextstep.courses.domain;

import java.util.Set;

public class NsImage {
    private static final String FILE_SIZE_TOO_LARGE = "파일 크기는 1MB를 초과할 수 없습니다.";
    private static final String NOT_ALLOWED_IMAGE_TYPE = "유효한 이미지 파일이 아닙니다.";
    private static final String INVALID_IMAGE_WIDTH_OR_HEIGHT = "유효한 이미지 사이즈가 아닙니다.";
    private static final String WIDTH_HEIGHT_RATIO_ONLY_3_2 = "이미지 width와 height의 비율은 3:2여야 합니다.";

    private static final long MAX_SIZE = 1_000_000; // 1MB
    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double EXPECTED_RATIO = 3.0 / 2.0;
    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/svg+xml"
    );

    private final long sizeInBytes;
    private final String contentType;
    private final int width;
    private final int height;

    public NsImage(long sizeInBytes, String contentType, int width, int height) {
        validate(sizeInBytes, contentType, width, height);
        this.sizeInBytes = sizeInBytes;
        this.contentType = contentType;
        this.width = width;
        this.height = height;
    }

    private void validate(long sizeInBytes, String contentType, int width, int height) {
        if (sizeInBytes > MAX_SIZE) {
            throw new IllegalArgumentException(FILE_SIZE_TOO_LARGE);
        }
        if (!ALLOWED_TYPES.contains(contentType.toLowerCase())) {
            throw new IllegalArgumentException(NOT_ALLOWED_IMAGE_TYPE);
        }
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_IMAGE_WIDTH_OR_HEIGHT);
        }

        double ratio = (double) width / height;
        if (ratio != EXPECTED_RATIO) {
            throw new IllegalArgumentException(WIDTH_HEIGHT_RATIO_ONLY_3_2);
        }
    }
}
