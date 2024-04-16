package nextstep.courses.domain;

import nextstep.courses.enums.ImageType;

public class CoverImage {

    private ImageFileSize imageFileSize;
    private ImageType imageType;
    private ImageSize imageSize;

    public CoverImage(ImageFileSize imageFileSize, ImageType imageType, ImageSize imageSize) {
        this.imageFileSize = imageFileSize;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    public static CoverImage of(int imageFileSize, String imageType, int width, int height) {
        ImageFileSize fileSize = ImageFileSize.of(imageFileSize);
        ImageType type = ImageType.of(imageType);
        ImageSize size = ImageSize.of(width, height);
        return new CoverImage(fileSize, type, size);
    }

    public int imageFileSize() {
        return imageFileSize.getImageFileSize();
    }

    public int imageWidth() {
        return imageSize.getWidth();
    }

    public int imageHeight() {
        return imageSize.getHeight();
    }

    public String imageType() {
        return imageType.getImageType();
    }
}

