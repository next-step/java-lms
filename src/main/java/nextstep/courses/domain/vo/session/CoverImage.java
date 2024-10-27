package nextstep.courses.domain.vo.session;

import nextstep.courses.domain.vo.session.image.ImageSize;
import nextstep.courses.domain.vo.session.image.ImageType;
import nextstep.courses.domain.vo.session.image.ImageWidthHeight;

import java.util.Objects;

public class CoverImage {
    private final ImageSize imageSize;
    private final ImageType imageType;
    private final ImageWidthHeight imageWidthHeight;

    public CoverImage(ImageSize imageSize, ImageType imageType, ImageWidthHeight imageWidthHeight) {
        this.imageSize = imageSize;
        this.imageType = imageType;
        this.imageWidthHeight = imageWidthHeight;
    }

    public CoverImage(int size, String imageTypeText, double width, double height) {
        this(new ImageSize(size), ImageType.toImageType(imageTypeText), new ImageWidthHeight(width, height));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return Objects.equals(imageSize, that.imageSize) && imageType == that.imageType && Objects.equals(imageWidthHeight, that.imageWidthHeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageSize, imageType, imageWidthHeight);
    }
}
