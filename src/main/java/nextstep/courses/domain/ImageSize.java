package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectRatioException;
import nextstep.courses.exception.SizeUnderException;

public class ImageSize {

    private static final long MIN_WIDTH_PIXEL = 300L;
    public static final long MIN_HEIGHT_PIXEL = 200L;
    private static final double ERROR_RATE = 0.001;
    private static final double EXPECTED_RATIO = 3.0 / 2.0;
    private final long widthPixel;
    private final long heightPixel;

    public ImageSize(long widthPixel,
                     long heightPixel) {
        validatePixel(widthPixel, heightPixel);
        validateRatio(widthPixel, heightPixel);

        this.widthPixel = widthPixel;
        this.heightPixel = heightPixel;
    }

    private void validatePixel(long widthPixel,
                               long heightPixel) {
        if (widthPixel < MIN_WIDTH_PIXEL) {
            throw new SizeUnderException("가로 픽셀은 300이상 입니다.");
        }

        if (heightPixel < MIN_HEIGHT_PIXEL) {
            throw new SizeUnderException("세로 픽셀은 200이상 입니다.");
        }
    }

    private void validateRatio(long widthPixel,
                               long heightPixel) {
        double ratio = (double) widthPixel / heightPixel;
        if (correctRatio(ratio)) {
            throw new IncorrectRatioException("가로 세로 비율은 3:2 입니다.");
        }
    }

    private static boolean correctRatio(double ratio) {
        return Math.abs(ratio - EXPECTED_RATIO) > ERROR_RATE;
    }
}
