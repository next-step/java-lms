package nextstep.courses.domain.image;

import nextstep.courses.InvalidImageFileException;

class ImageSize {
    private final static int MAX_SIZE = 1_024; // 1024 Byte
    private final int size;

    ImageSize(int size) {
        if (!isValidSize(size)) {
            throw new InvalidImageFileException(String.format("이미지 사이즈는 %sB 이하여야 합니다.", MAX_SIZE));
        }
        this.size = size;
    }

    private boolean isValidSize(int size) {
        return size <= MAX_SIZE;
    }
}
