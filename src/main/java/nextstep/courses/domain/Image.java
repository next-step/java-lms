package nextstep.courses.domain;

public class Image {
    private static final int MAX_IMAGE_SIZE = 1024 * 1024;
    private static final int WIDTH_LIMIT = 300;
    private static final int HEIGHT_LIMIT = 200;
    private static final double REQUIRED_ASPECT_RATIO = 1.5;
    private static final double ASPECT_RATIO_TOLERANCE = 0.01;
    private String fileName;
    private String contentType;
    private long sizeInBytes;
    private int width;
    private int height;

    public Image(String fileName, String contentType, long sizeInBytes, int width, int height) {
        validate(contentType, sizeInBytes, width, height);
        this.fileName = fileName;
        this.contentType = contentType;
        this.sizeInBytes = sizeInBytes;
        this.width = width;
        this.height = height;
    }

    private void validate(String contentType, long sizeInBytes, int width, int height) {
        if (sizeInBytes > MAX_IMAGE_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }

        if (!contentType.matches("(gif|jpeg|jpg|png|svg)")) {
            throw new IllegalArgumentException("허용되지 않는 이미지 형식입니다.");
        }

        if (width < WIDTH_LIMIT || height < HEIGHT_LIMIT) {
            throw new IllegalArgumentException("이미지 크기가 너무 작습니다.");
        }

        double ratio = (double) width / height;
        if (Math.abs(ratio - REQUIRED_ASPECT_RATIO) > ASPECT_RATIO_TOLERANCE) {
            throw new IllegalArgumentException("이미지 비율은 3:2여야 합니다.");
        }
    }
}
