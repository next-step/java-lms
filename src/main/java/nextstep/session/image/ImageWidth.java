package nextstep.session.image;

import java.util.Objects;

public class ImageWidth {

    private static final int MINIMUM_WIDTH = 300;
    private static final String OVER_WIDTH_MESSAGE = "이미지의 너비가 300px 이상이여야 합니다.";

    private final int width;

    public ImageWidth(int width) {
        confirmWidthHeight(width);
        this.width = width;
    }

    private void confirmWidthHeight(int width) {
        if (width < MINIMUM_WIDTH) {
            throw new IllegalArgumentException(OVER_WIDTH_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImageWidth that = (ImageWidth) object;
        return width == that.width;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(width);
    }
}
