package nextstep.sessions.domain.data.vo;

import nextstep.sessions.domain.data.type.ImageType;

public class CoverImage {

    private final ImageType imageType;
    private final ImageSize imageSize;

    public CoverImage(String fileName, int fileSize, int width, int height) {
        this.imageType = ImageType.valueOfName(fileName);
        this.imageSize = new ImageSize(fileSize, width, height);
    }

}
