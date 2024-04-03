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
        if (!capacity.satisfy(1)) {
            throw new IllegalArgumentException("capacity must under 1mb");
        }
        this.capacity = capacity;
    }

    public CoverImage(ImageFile imageFile) {
        if (!imageFile.satisfy(Extension.PNG, Extension.GIF, Extension.JPG, Extension.JPEG, Extension.SVG)) {
            throw new IllegalArgumentException("capacity must under 1mb");
        }
        this.imageFile = imageFile;
    }

    public CoverImage(ImageSize imageSize) {
        if (!imageSize.biggerThanEqual(300, 200)
                && !imageSize.satisfyProportion(3, 2)) {
            throw new IllegalArgumentException("capacity must under 1mb");
        }
        this.imageSize = imageSize;
    }
}
