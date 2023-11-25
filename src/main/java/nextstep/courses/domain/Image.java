package nextstep.courses.domain;

import nextstep.courses.ImageVolumeOverException;
import nextstep.courses.domain.type.ImageType;

public class Image {

    private final long volume;
    private final ImageType type;
    private final ImageSpecification specification;

    public Image(long volume, String type, int width, int height) {
        this.volume = volume;
        this.type = ImageType.of(type);
        this.specification = new ImageSpecification(width, height);
        validate();
    }

    private void validate() {
        if (this.volume > 1) {
            throw new ImageVolumeOverException("이미지 최대 크기를 초과했습니다.");
        }
    }

}
