package nextstep.courses.domain.field;

public class CoverImage {

    private long size;

    private long width;

    private long height;

    private ImageType imageType;

    public CoverImage(long size,
                      long width,
                      long height,
                      ImageType imageType) {
        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }
}
