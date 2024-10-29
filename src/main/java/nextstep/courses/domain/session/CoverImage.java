package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.ImageFileSize;
import nextstep.courses.domain.session.image.ImageType;
import nextstep.courses.domain.session.image.ImageSize;

import java.util.Objects;

public class CoverImage {
    private final ImageFileSize imageFileSize;
    private final ImageType imageType;
    private final ImageSize imageSize;

    public CoverImage(ImageFileSize imageFileSize, ImageType imageType, ImageSize imageSize) {
        this.imageFileSize = imageFileSize;
        this.imageType = imageType;
        this.imageSize = imageSize;
    }

    public CoverImage(int size, String imageTypeText, double width, double height) {
        this(new ImageFileSize(size), ImageType.toImageType(imageTypeText), new ImageSize(width, height));
    }

    public ImageFileSize getImageFileSize() {
        return imageFileSize;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public ImageSize getImageSize() {
        return imageSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(imageFileSize, that.imageFileSize) && imageType == that.imageType && Objects.equals(imageSize, that.imageSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageFileSize, imageType, imageSize);
    }


}
