package nextstep.courses.domain;

public class CoverImage {
    private final Long size;

    public CoverImage() {
        this(0L);
    }

    public CoverImage(Long size) {
        if (size > 1_048_576) {
            throw new IllegalArgumentException("커버 이미지의 크기는 1MB 이하여야 합니다.");
        }

        this.size = size;
    }
}
