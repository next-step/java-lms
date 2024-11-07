package nextstep.courses.domain.cover;

public class CoverImage {

    private final String fileName;
    private final ImageSize imageSize;
    private final ImageExtension extension;
    private final ImageDimension imageDimension;

    private CoverImage(String fileName, ImageSize imageSize, String extension, ImageDimension imageDimension) {
        this.fileName = fileName;
        this.imageSize = imageSize;
        this.extension = ImageExtension.getExtension(extension);
        this.imageDimension = imageDimension;
    }

    public static CoverImage of(String filename, ImageSize imageSize, String extension, ImageDimension imageDimension) {
        return new CoverImage(filename, imageSize, extension, imageDimension);
    }

    public String getFileName() {
        return fileName;
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
