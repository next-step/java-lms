package nextstep.sessions.domain;

import java.util.Objects;

public class ImageRatio {
    private final double value;

    public static ImageRatio ratio(ImageSize width, ImageSize height) {
        return new ImageRatio(width.doubleValue() / height.doubleValue());
    }

    public ImageRatio(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageRatio that = (ImageRatio) o;
        return Double.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
