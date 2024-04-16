package nextstep.courses.domain;

public class ImageFileSize {

    private static final int MAX_FILE_SIZE = 1024;
    private static final String INVALID_IMAGE_FILE_SIZE = "이미지 사이즈는 1MB 이하만 가능합니다.";

    private int imageFileSize;

    private ImageFileSize(int imageFileSize) {
        this.imageFileSize = imageFileSize;
    }

    public static ImageFileSize of(int imageFileSize) {

        if (!isValidFileSize(imageFileSize)) {
            throw new IllegalStateException(INVALID_IMAGE_FILE_SIZE);
        }

        return new ImageFileSize(imageFileSize);
    }

    private static boolean isValidFileSize(int fileSize) {
        return MAX_FILE_SIZE >= fileSize;
    }

    public int getImageFileSize() {
        return imageFileSize;
    }
}
