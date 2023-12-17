package nextstep.courses.domain.session.image;

import nextstep.courses.domain.session.constant.ImageTypeEnum;

public class Image {
    private ImageFileSize fileSize;
    private ImageSize imageSize;
    private ImageTypeEnum type;

    public Image(int fileSize, String type, int width, int height) {
        this.fileSize = new ImageFileSize(fileSize);
        imageSize = new ImageSize(width, height);
        this.type = ImageTypeEnum.findByType(type);
    }
}
