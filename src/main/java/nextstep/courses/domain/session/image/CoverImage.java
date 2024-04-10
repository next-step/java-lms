package nextstep.courses.domain.session.image;

public class CoverImage {

    private ImageTypeEnum imageType;
    private ImageFileSize imageFileSize;
    private ImageSize imageSize;

    private CoverImage(ImageTypeEnum imageType, ImageFileSize imageFileSize, ImageSize imageSize) {
        this.imageType = imageType;
        this.imageFileSize = imageFileSize;
        this.imageSize = imageSize;
    }

    public static CoverImage of(String imageType, int imageFileSize, int width, int height) {
        ImageTypeEnum imageTypeEnum = ImageTypeEnum.of(imageType);
        ImageFileSize fileSize = ImageFileSize.of(imageFileSize);
        ImageSize imageSize = ImageSize.of(width, height);

        return new CoverImage(imageTypeEnum, fileSize, imageSize);
    }

    public String getImageType() {
        return imageType.getImageType();
    }

    public int getImageFileSize() {
        return imageFileSize.getImageFileSize();
    }

    public int getImageSizeWidth() {
        return imageSize.getWidth();
    }

    public int getImageSizeHeight() {
        return imageSize.getHeight();
    }

}
