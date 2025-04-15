package nextstep.courses.domain;

import java.util.Objects;

public class NsImageSize {
    private static final String INVALID_IMAGE_WIDTH_OR_HEIGHT = "유효한 이미지 사이즈가 아닙니다.";
    private static final String WIDTH_HEIGHT_RATIO_ONLY_3_2 = "이미지 width와 height의 비율은 3:2여야 합니다.";

    private static final int MIN_WIDTH = 300;
    private static final int MIN_HEIGHT = 200;
    private static final double EXPECTED_RATIO = 3.0 / 2.0;

    private final int width;
    private final int height;

    public NsImageSize(int width, int height) {
        validate(width, height);
        this.width = width;
        this.height = height;
    }

    private void validate(int width, int height) {
        if (width < MIN_WIDTH || height < MIN_HEIGHT) {
            throw new IllegalArgumentException(INVALID_IMAGE_WIDTH_OR_HEIGHT);
        }

        double ratio = (double) width / height;
        if (ratio != EXPECTED_RATIO) {
            throw new IllegalArgumentException(WIDTH_HEIGHT_RATIO_ONLY_3_2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NsImageSize that = (NsImageSize) o;
        return width == that.width && height == that.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
