package nextstep.courses.domain.session.image;

public class CoverImage {

    private Long id;
    private ImageTypeEnum imageType;
    private ImageFileSize imageFileSize;
    private ImageSize imageSize;

    private CoverImage(Long id, ImageTypeEnum imageType, ImageFileSize imageFileSize, ImageSize imageSize) {
        this.id = id;
        this.imageType = imageType;
        this.imageFileSize = imageFileSize;
        this.imageSize = imageSize;
    }

    public static CoverImage of(String imageType, int imageFileSize, int width, int height) {
        return of(null, imageType, imageFileSize, width, height);
    }

    public static CoverImage of(Long id, String imageType, int imageFileSize, int width, int height) {
        ImageTypeEnum imageTypeEnum = ImageTypeEnum.of(imageType);
        ImageFileSize fileSize = ImageFileSize.of(imageFileSize);
        ImageSize imageSize = ImageSize.of(width, height);

        return new CoverImage(id, imageTypeEnum, fileSize, imageSize);
    }

    public Long getId() {
        return id;
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
