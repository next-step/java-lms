package nextstep.courses.domain.session.cover;

public class CoverImage {
    private final ImageSize imageSize;
    private final String fileName;
    private final ImageType imageType;
    private final ImageDimension imageDimension;

    public CoverImage(int size, String fileName, int width, int height) {
        this(new ImageSize(size), fileName, ImageType.fromFileName(fileName), new ImageDimension(width, height));
    }

    private CoverImage(ImageSize imageSize, String fileName, ImageType imageType, ImageDimension imageDimension) {
        this.imageSize = imageSize;
        this.fileName = fileName;
        this.imageType = imageType;
        this.imageDimension = imageDimension;
    }

    public int getImageSize() {
        return imageSize.value();
    }

    public String getImageName() {
        return fileName;
    }

    public int getCoverImageWidth() {
        return imageDimension.width();
    }

    public int getCoverImageHeight() {
        return imageDimension.height();
    }
}
