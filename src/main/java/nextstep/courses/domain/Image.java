package nextstep.courses.domain;

import nextstep.courses.domain.type.ImageType;

import java.time.LocalDateTime;
import java.util.Objects;

public class Image extends BaseEntity {

    private final Long id;
    private final Volume volume;
    private final ImageType type;
    private final ImageSpecification specification;

    public Image(double volume, String type, int width, int height) {
        this(0L, volume, type, width, height, LocalDateTime.now(), null);
    }

    public Image(Long id, double volume, String type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.volume = new Volume(volume);
        this.type = ImageType.of(type);
        this.specification = new ImageSpecification(width, height);
    }

    public Volume volume() {
        return this.volume;
    }

    public ImageType type() {
        return this.type;
    }

    public ImageSpecification specification() {
        return this.specification;
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

    @Override
    public String toString() {
        return "Image{" +
            "id=" + id +
            ", volume=" + volume +
            ", type=" + type +
            ", specification=" + specification +
            '}';
    }
}
