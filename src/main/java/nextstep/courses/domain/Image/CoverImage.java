package nextstep.courses.domain.Image;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class CoverImage extends BaseEntity {
    private final String url;
    private final Volume volume;
    private final ImageFormat format;
    private final AspectRatio aspectRatio;

    public CoverImage(String url, long volume, String type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(0L, url, volume, type, width, height, createdAt, updatedAt);
    }

    public CoverImage(String url, long volume, String type, int width, int height, LocalDateTime createdAt) {
        this(0L, url, volume, type, width, height, createdAt, null);
    }

    public CoverImage(long id, String url, long volume, String type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.url = url;
        this.volume = new Volume(volume);
        this.format = ImageFormat.findBy(type);
        this.aspectRatio = new AspectRatio(width, height);
    }

    public String url() {
        return url;
    }

    public long volume() {
        return volume.find();
    }

    public String format() {
        return format.toString();
    }

    public AspectRatio aspectRatio() {
        return aspectRatio;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(volume, that.volume) && format == that.format && Objects.equals(aspectRatio, that.aspectRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, format, aspectRatio);
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "id=" + id() +
                ", url='" + url + '\'' +
                ", volume=" + volume +
                ", format=" + format +
                ", aspectRatio=" + aspectRatio +
                ", createdAt= " + createdAt() +
                ", updateAt=" + updatedAt() +
                '}';
    }
}
