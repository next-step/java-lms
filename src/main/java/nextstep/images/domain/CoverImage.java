package nextstep.images.domain;

public class CoverImage {

    private static final int MAX_SIZE = 1;

    private double size;

    public CoverImage(final double size) {
        validateSize(size);
        this.size = size;
    }

    private void validateSize(final double size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB를 넘을 수 없습니다.");
        }
    }
}
