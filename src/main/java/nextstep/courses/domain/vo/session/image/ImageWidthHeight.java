package nextstep.courses.domain.vo.session.image;

import nextstep.courses.ImageWidthHeightRatioMismatchException;
import nextstep.courses.WidthHeightMinimumException;

import java.util.Objects;

public class ImageWidthHeight {

    public static final String WIDTH_HEIGHT_MINIMUM_MESSAGE = "이미지의 width는 300픽셀, height는 200픽셀 이상";
    public static final String IMAGE_DIMENSION_MISMATCH_MESSAGE = "width와 height의 비율은 3:2여야 한다";
    public static final int MIN_WIDTH = 300;
    public static final int MIN_HEIGHT = 200;
    public static final double WIDTH_HEIGHT_RATIO = 1.5d;
    private final double width;
    private final double height;

    public ImageWidthHeight(double width, double height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new WidthHeightMinimumException(WIDTH_HEIGHT_MINIMUM_MESSAGE);
        }
        boolean ratio = width / height == WIDTH_HEIGHT_RATIO;
        if (!ratio) {
            throw new ImageWidthHeightRatioMismatchException(IMAGE_DIMENSION_MISMATCH_MESSAGE);
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageWidthHeight that = (ImageWidthHeight) o;
        return Double.compare(that.width, width) == 0 && Double.compare(that.height, height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
