package nextstep.courses.domain;

import nextstep.courses.exception.ImageVolumeOverException;
import nextstep.courses.domain.type.ImageType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return volume == image.volume && type == image.type && Objects.equals(specification, image.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, type, specification);
    }
}
