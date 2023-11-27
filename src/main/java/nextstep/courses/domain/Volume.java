package nextstep.courses.domain;

import nextstep.courses.exception.ImageVolumeOverException;

import java.util.Objects;

public class Volume {

    private final double volume;

    public Volume(double volume) {
        this.volume = volume;
        validate();
    }

    private void validate() {
        if (this.volume > 1.0) {
            throw new ImageVolumeOverException("이미지 최대 크기를 초과했습니다.");
        }
    }

    public double volume() {
        return this.volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume1 = (Volume) o;
        return Double.compare(volume1.volume, volume) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume);
    }

    @Override
    public String toString() {
        return "Volume{" +
            "volume=" + volume +
            '}';
    }
}
