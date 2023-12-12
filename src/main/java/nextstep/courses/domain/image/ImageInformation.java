package nextstep.courses.domain.image;

import nextstep.courses.exception.ExceedLimitedVolumeException;

import java.util.Objects;

public class ImageInformation {

    private static final long MAXIMUM_VOLUME = 1024 * 1024;

    private final ImageSize imageSize;

    private final long volume;

    private final ImageFormat imageFormat;

    public ImageInformation(ImageSize imageSize, long volume, ImageFormat imageFormat) {
        validateVolume(volume);
        this.imageSize = imageSize;
        this.volume = volume;
        this.imageFormat = imageFormat;
    }

    private void validateVolume(long volume) {
        if (volume > MAXIMUM_VOLUME) {
            throw new ExceedLimitedVolumeException(volume);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageInformation that = (ImageInformation) o;
        return volume == that.volume && Objects.equals(imageSize, that.imageSize) && imageFormat == that.imageFormat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageSize, volume, imageFormat);
    }
}
