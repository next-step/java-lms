package nextstep.courses.domain;

import nextstep.courses.ImageSizeOverException;
import nextstep.courses.domain.type.ImageType;

public class Image {

    private final long size;
    private final ImageType type;

    public Image(long size, String type) {
        this.size = size;
        this.type = ImageType.of(type);
        validate();
    }

    private void validate() {
        if (this.size > 1) {
            throw new ImageSizeOverException("이미지 최대 크기를 초과했습니다.");
        }
    }

}
