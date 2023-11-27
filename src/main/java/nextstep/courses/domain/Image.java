package nextstep.courses.domain;

import nextstep.courses.domain.type.ImageType;

import java.util.Objects;

public class Image {

    private final Volume volume;
    private final ImageType type;
    private final ImageSpecification specification;

    public Image(double volume, String type, int width, int height) {
        this.volume = new Volume(volume);
        this.type = ImageType.of(type);
        this.specification = new ImageSpecification(width, height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(volume, image.volume) && type == image.type && Objects.equals(specification, image.specification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, type, specification);
    }
}
