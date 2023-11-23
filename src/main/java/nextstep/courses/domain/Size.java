package nextstep.courses.domain;

import nextstep.courses.IncorrectRatioException;

public class Size {

    private static final double ERROR_RATE = 0.001;
    private static final double EXPECTED_RATIO = 3.0 / 2.0;
    private final Width width;
    private final Height height;

    public Size(Width width,
                Height height) {
        validateRatio(width, height);

        this.width = width;
        this.height = height;
    }

    private void validateRatio(Width width,
                               Height height) {
        double ratio = width.ratio(height);
        if (correctRatio(ratio)) {
            throw new IncorrectRatioException("가로 세로 비율은 3:2 입니다.");
        }
    }

    private static boolean correctRatio(double ratio) {
        return Math.abs(ratio - EXPECTED_RATIO) > ERROR_RATE;
    }
}
