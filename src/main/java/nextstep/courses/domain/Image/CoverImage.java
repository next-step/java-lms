package nextstep.courses.domain.Image;

import nextstep.courses.domain.BaseEntity;

import java.time.LocalDateTime;
import java.util.Objects;

public class CoverImage extends BaseEntity {

    /**
     * 이미지 저장을 url 주소값으로 한다는 전제로 구현
     */
    private final Long id;
    private final String url;
    private final Volume volume;
    private final ImageFormat format;
    private final AspectRatio aspectRatio;


    public CoverImage(String url, long volume, String type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this(null, url, volume, type, width, height, createdAt, updatedAt);
    }


    public CoverImage(String url, long volume, String type, int width, int height, LocalDateTime createdAt) {
        this(null, url, volume, type, width, height, createdAt, null);
    }

    public CoverImage(Long id, String url, long volume, String type, int width, int height, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.url = url;
        this.volume = new Volume(volume);
        this.format = ImageFormat.findBy(type);
        this.aspectRatio = new AspectRatio(width, height);
    }

    public Long id() {
        return id;
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
        return Objects.equals(url, that.url) && Objects.equals(volume, that.volume) && format == that.format && Objects.equals(aspectRatio, that.aspectRatio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, volume, format, aspectRatio);
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
