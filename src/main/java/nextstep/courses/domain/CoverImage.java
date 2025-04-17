package nextstep.courses.domain;

public class CoverImage {
    private final Size size;
    private final Width width;
    private final Height height;
    private final ImageType type;

    public static CoverImage defaultImage() {
        return new CoverImage(0L, 300.0, 200.0, ImageType.JPEG);
    }

    public CoverImage(Long size, Double width, Double height, ImageType type) {
        if (width / height != 1.5) {
            throw new IllegalArgumentException("커버 이미지의 가로 세로 비율은 3:2 이어야 합니다.");
        }

        this.size = new Size(size);
        this.width = new Width(width);
        this.height = new Height(height);
        this.type = type;
    }
}
