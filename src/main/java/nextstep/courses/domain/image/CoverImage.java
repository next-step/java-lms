package nextstep.courses.domain.image;

public class CoverImage {

    private final ImageFile imageFile;
    private final ImageDimension imageDimension;

    public CoverImage(String fileName, long imageSize, int width, int height) {
        this(new ImageFile(fileName, imageSize), new ImageDimension(width, height));
    }

    public CoverImage(ImageFile imageFile, ImageDimension imageDimension) {
        this.imageFile = imageFile;
        this.imageDimension = imageDimension;
    }
}
