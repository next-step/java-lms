package nextstep.courses.domain.Image;

import java.util.Objects;

public class CoverImage {
    private final Volume volume;
    private final ImageFormat format;
    private final AspectRatio aspectRatio;

    public CoverImage(long volume, String type, int width, int height) {

        this.volume = new Volume(volume);
        this.format = ImageFormat.findBy(type);
        this.aspectRatio = new AspectRatio(width, height);
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
}
