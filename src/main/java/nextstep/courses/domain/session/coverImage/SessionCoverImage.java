package nextstep.courses.domain.session.coverImage;

public class SessionCoverImage {
    private ImageSize imageSize;
    private ImageExtension imageExtension;
    private ImageDimensions imageDimensions;

    public SessionCoverImage(int size, ImageExtension imageExtension, int width, int height) {
        this(new ImageSize(size), imageExtension, new ImageDimensions(width, height));
    }

    public SessionCoverImage(ImageSize imageSize, ImageExtension imageExtension, ImageDimensions imageDimensions) {
        this.imageSize = imageSize;
        this.imageExtension = imageExtension;
        this.imageDimensions = imageDimensions;
    }

    public boolean isValidCoverImage() {
        return imageSize.validSize()
                && ImageExtension.validExtension(imageExtension)
                && imageDimensions.validDimensions();
    }
}
