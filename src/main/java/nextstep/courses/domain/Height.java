package nextstep.courses.domain;

import nextstep.courses.SizeUnderException;

public class Height {
    public static final long MIN_PIXEL = 200L;
    private final long pixel;

    public Height(long pixel) {
        if (pixel < MIN_PIXEL) {
            throw new SizeUnderException("세로 픽셀은 200이상 입니다.");
        }

        this.pixel = pixel;
    }

    public double divisionInput(long pixel) {
        return (double) pixel / this.pixel;
    }
}
