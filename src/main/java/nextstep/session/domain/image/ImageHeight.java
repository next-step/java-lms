package nextstep.session.domain.image;

import java.util.Objects;

public class ImageHeight {

    private static final int MINIMUM_HEIGHT = 200;
    private static final String OVER_HEIGHT_MESSAGE = "이미지의 높이가 200px 이상이여야 합니다.";

    private final int height;

    public ImageHeight(int height) {
        confirmHeight(height);
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    private void confirmHeight(int height) {
        if (height < MINIMUM_HEIGHT) {
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
