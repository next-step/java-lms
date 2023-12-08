package nextstep.image.domain;

import java.util.Objects;

public class Image {

    private ImageCapacity imageCapacity;

    private ImageType imageType;

    private ImageSize imageSize;

    public Image(ImageCapacity imageCapacity, ImageType imageType, ImageSize imageSize) {
        this.imageCapacity = imageCapacity;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return Objects.equals(imageCapacity, image.imageCapacity) && imageType == image.imageType
                && Objects.equals(imageSize, image.imageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageCapacity, imageType, imageSize);
    }
}
