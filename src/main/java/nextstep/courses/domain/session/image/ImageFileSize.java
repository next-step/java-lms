package nextstep.courses.domain.session.image;

public class ImageFileSize {
    private int size;

    public ImageFileSize(int size) {
        this.size = checkValidSize(size);
    }

    private int checkValidSize(int size) {
        if (1 < size) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다. 더 작은 이미지 크기를 입력해주세요");
        }
        return size;
    }
}
