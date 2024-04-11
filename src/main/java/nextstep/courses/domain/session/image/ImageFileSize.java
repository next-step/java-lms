package nextstep.courses.domain.session.image;

public class ImageFileSize {
    public static final int ONE_MEGA_BYTE = 1_024;
    public static final String INVALID_IMAGE_FILE_SIZE = "이미지 파일 사이즈는 1MB 이하만 가능합니다.";

    private int imageFileSize;   // 단위 : KB

    private ImageFileSize(int fileSize) {
        this.imageFileSize = fileSize;
    }

    public static ImageFileSize of(int fileSize) {
        if (!isValidFileSize(fileSize)) {
            throw new IllegalArgumentException(INVALID_IMAGE_FILE_SIZE);
        }

        return new ImageFileSize(fileSize);
    }

    private static boolean isValidFileSize(int fileSize) {
        if (ONE_MEGA_BYTE < fileSize) {
            return false;
        }

        return true;
    }

    public int getImageFileSize() {
        return imageFileSize;
    }

}
