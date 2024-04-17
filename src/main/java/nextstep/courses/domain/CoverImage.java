package nextstep.courses.domain;

public class CoverImage {
    private Long Size;
    private ImageExtension imageExtension;
    private Long width;
    private Long hight;

    public CoverImage() {
        this(0L, ImageExtension.GIF.name(), 300L, 200L);
    }

    public CoverImage(Long size, String extension, Long width, Long hight) {
        if (size > 1024L) {
            throw new IllegalArgumentException("파일 사이즈는 1MB 이하여야 합니다");
        }

        if (!(width >= 300L && hight >= 200L && (width * 2 == hight * 3))) {
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상, width와 height의 비율은 3:2여야 한다");
        }

        Size = size;
        this.imageExtension = ImageExtension.of(extension);
        this.width = width;
        this.hight = hight;
    }
}
