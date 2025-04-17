package nextstep.courses.domain;

public class CoverImage {

    private int width;
    private int height;
    private ImageType imageType;
    private long size;

    CoverImage(){

    }

    public CoverImage(String imageType, long size, int width, int height) {
        if (size > 1_000) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
        if (width < 300) {
            throw new IllegalArgumentException("Width는 300픽셀 이상이여야 합니다.");
        }
        if (height < 200) {
            throw new IllegalArgumentException("Height는 200픽셀 이상이여야 합니다.");
        }
        if ((double) width / (double) height < 1.5) {
            throw new IllegalArgumentException("Width와 Height의 비율은 3:2여야 합니다.");
        }
        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = ImageType.getCoverImageType(imageType);
    }

    public long getSize() {
        return size;
    }

    public ImageType getImageType() {
        return this.imageType;
    }
}
