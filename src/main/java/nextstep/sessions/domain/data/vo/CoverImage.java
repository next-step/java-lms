package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.ImageType;

public class CoverImage {

    private final ImageType imageType;
    private final ImageSize imageSize;

    public CoverImage(String fileName, int fileSize, int width, int height) {
        this(ImageType.valueOfName(fileName), new ImageSize(fileSize, width, height));
    }

    public CoverImage(ImageType imageType, ImageSize imageSize) {
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    public ImageType imageType() {
        return imageType;
    }

    public ImageSize imageSize() {
        return imageSize;
    }

    public String imageTypeName() {
        return imageType.name();
    }

    public int fileSize() {
        return imageSize.fileSize();
    }

    public int width() {
        return imageSize.pixelSize().width();
    }

    public int height() {
        return imageSize().pixelSize().height();
    }
}
