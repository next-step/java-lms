package nextstep.image.domain;

public class ImageSize {
    private int size;

    public ImageSize(int size) {
        validateSize(size);
        this.size = size;
    }

    private void validateSize(final int size) {
        if (size >= 1000000) {
            throw new IllegalArgumentException("이미지 크기는 1메가 바이트를 넘을 수 없습니다.");
        }
    }
}
