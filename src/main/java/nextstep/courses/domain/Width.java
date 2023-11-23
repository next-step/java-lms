package nextstep.courses.domain;

import nextstep.courses.SizeUnderException;

public class Width {

    public static final long MIN_PIXEL = 300L;
    private final long pixel;

    public Width(long pixel) {
        if (pixel < MIN_PIXEL) {
            throw new SizeUnderException("가로 픽셀은 300이어야 합니다.");
        }

        this.pixel = pixel;
    }
}
