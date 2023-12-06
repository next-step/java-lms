package nextstep.courses.domain.image;

import nextstep.courses.exception.ExceedLimitedVolumeException;

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
}
