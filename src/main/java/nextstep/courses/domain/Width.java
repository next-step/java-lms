package nextstep.courses.domain;

import nextstep.courses.exception.SizeUnderException;

public class Width {

    private static final long MIN_PIXEL = 300L;
    private final long pixel;

    public Width(long pixel) {
        if (pixel < MIN_PIXEL) {
            throw new SizeUnderException("가로 픽셀은 300이상 입니다.");
        }

        this.pixel = pixel;
    }

    public double ratio(Height height) {
        return height.divisionInput(pixel);
    }
}
