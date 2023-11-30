package nextstep.courses.domain.Image;

import nextstep.courses.exception.ImageException;

import java.util.Objects;

public class Volume {
    private final long value;

    public Volume(long value) {
        validate(value);
        this.value = value;
    }

    private void validate(long value) {
        if (value > 1000_000L) {
            throw new ImageException("이미지 크기는 1MB 이하여야 합니다");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return value == volume.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
