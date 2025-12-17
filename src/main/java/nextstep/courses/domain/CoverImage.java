package nextstep.courses.domain;

public class CoverImage {
    private ImageSize imageSize;
    private ImageType imageType;
    private ImageDimension imageDimension;

    public CoverImage(int size, String fileName, int width, int height) {
        this(new ImageSize(size), ImageType.fromFileName(fileName), new ImageDimension(width, height));
    }

    private CoverImage(ImageSize imageSize, ImageType imageType, ImageDimension imageDimension) {
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageDimension = imageDimension;
    }
}
