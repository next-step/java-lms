package nextstep.courses.domain.cover;

public class CoverImage {

    private ImageSize imageSize;
    private ImageExtension extension;
    private ImageDimension imageDimension;

    private CoverImage(ImageSize imageSize, String extension, ImageDimension imageDimension) {
        this.imageSize = imageSize;
        this.extension = ImageExtension.getExtension(extension);
        this.imageDimension = imageDimension;
    }

    public static CoverImage of(ImageSize imageSize, String extension, ImageDimension imageDimension) {
        return new CoverImage(imageSize, extension, imageDimension);
    }

    public int getImageSize() {
        return imageSize.getImageSize();
    }

    public ImageExtension getExtension() {
        return extension;
    }

    public int getWidth() {
        return imageDimension.getWidth();
    }

    public int getHeight() {
        return imageDimension.getHeight();
    }
}
