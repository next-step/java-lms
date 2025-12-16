package nextstep.courses.domain;

public class CoverImage {
    private ImageSize imageSize;
    private ImageType imageType;
    private ImageDimension imageDimension;

    public CoverImage(int size, ImageType imageType, int width, int height) {
        this(new ImageSize(size), imageType, new ImageDimension(width, height));
    }

    public CoverImage(ImageSize imageSize, ImageType imageType, ImageDimension imageDimension) {
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageDimension = imageDimension;
    }
}
