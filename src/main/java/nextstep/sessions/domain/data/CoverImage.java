package nextstep.sessions.domain.data;

import nextstep.sessions.domain.data.type.ImageType;
import nextstep.sessions.domain.data.vo.ImageSize;

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

    public String imageTypeName() {
        return imageType.name();
    }

    public int fileSize() {
        return imageSize.fileSize();
    }

    public int width() {
        return imageSize.width();
    }

    public int height() {
        return imageSize.height();
    }
}
