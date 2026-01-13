package nextstep.courses.domain.image;

import nextstep.courses.FileSizeException;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ImageSize imageSize = (ImageSize) o;
        return size == imageSize.size;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size);
    }
}
