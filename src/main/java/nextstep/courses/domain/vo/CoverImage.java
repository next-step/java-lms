package nextstep.courses.domain.vo;

public class CoverImage {
    private ImageSize imageSize;
    private ImageFile imageFile;
    private Capacity capacity;

    public CoverImage(ImageSize imageSize, ImageFile imageFile, Capacity capacity) {
        this.imageSize = imageSize;
        this.imageFile = imageFile;
        this.capacity = capacity;
    }

    public CoverImage(Capacity capacity) {
        this.capacity = capacity;
    }

    public CoverImage(ImageFile imageFile) {
        this.imageFile = imageFile;
    }

    public CoverImage(ImageSize imageSize) {
        this.imageSize = imageSize;
    }
}
