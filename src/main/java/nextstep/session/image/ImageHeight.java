package nextstep.session.image;

import java.util.Objects;

public class ImageHeight {

    private static final int MAXIMUM_HEIGHT = 200;
    private static final String OVER_HEIGHT_MESSAGE = "이미지의 높이가 200px을 초과하면 안됩니다.";

    private final int height;

    public ImageHeight(int height) {
        confirmHeight(height);
        this.height = height;
    }

    private void confirmHeight(int height) {
        if (height > MAXIMUM_HEIGHT) {
            throw new IllegalArgumentException(OVER_HEIGHT_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ImageHeight that = (ImageHeight) object;
        return height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(height);
    }
}
