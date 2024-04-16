package nextstep.courses.domain.session.image;

import java.util.Objects;

public class Size {

    static final long MINIMUM_SIZE = 1L;
    static final long MAXIMUM_SIZE = 1048576L;

    private final long bytes;

    public Size(final long bytes) {
        if (bytes < MINIMUM_SIZE || bytes > MAXIMUM_SIZE) {
            throw new IllegalArgumentException("커버 이미지 크기는 0MB 이상 1MB 이하여야 합니다. 크기: " + bytes);
        }

        this.bytes = bytes;
    }

    @Override
    public boolean equals(final Object otherSize) {
        if (this == otherSize) {
            return true;
        }

        if (otherSize == null || getClass() != otherSize.getClass()) {
            return false;
        }

        return this.bytes == ((Size)otherSize).bytes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.bytes);
    }
}
