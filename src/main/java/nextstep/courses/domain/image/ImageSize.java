package nextstep.courses.domain.image;

import nextstep.courses.FileSizeException;

public class ImageSize {
    private static final int MAX_FILE_SIZE = 1024 * 1024;
    private static final int MB = 1024 * 1024;
    private final int size;

    public ImageSize(int size) {
        validateFileSize(size);

        this.size = size;
    }

    private void validateFileSize(int size) {
        if (size > MAX_FILE_SIZE) {
            throw new FileSizeException(String.format("이미지 파일은 %dMB를 넘을 수 없습니다.", MAX_FILE_SIZE / MB));
        }
    }
}
