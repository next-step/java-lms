package nextstep.courses.domain.image;

import java.util.Objects;

public class ImageSize {

    private static final int MAX_SIZE = 1024 * 1024;

    private final int volume;

    public ImageSize(int value) {
        validate(value);
        this.volume = value;
    }

    private void validate(int value) {
        if (value <= 0 || value > MAX_SIZE) {
            throw new IllegalArgumentException("파일용량을 확인하세요.");
        }
    }

    public int volume() {
        return volume;
    }

    @Override
    public boolean equals(Object obj) {
        if ( this == obj ) return true;
        if ( obj == null || getClass() != obj.getClass() ) return false;
        ImageSize imageSize = (ImageSize)obj;
        return Objects.equals(volume, imageSize.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume);
    }
}
