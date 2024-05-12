package nextstep.courses.domain.Image;

import nextstep.courses.ImageSizeRangeException;
import nextstep.courses.ImageSizeRatioException;

public class ImageSize {

    private static int WIDTH_RATIO = 3;
    private static int HEIGHT_RATIO = 2;

    private final int width;
    private final int height;

    public ImageSize(int width, int height) throws Exception {
        validSizeRange(width, height);
        validSizeRatio(width, height);
        this.width = width;
        this.height = height;
    }

    private void validSizeRange(int width, int height) throws ImageSizeRangeException {
        if (width < 300 || height < 200) {
            throw new ImageSizeRangeException("가로 300 이상 세로 200 이상이어야 합니다.");
        }
    }

    private void validSizeRatio(int width, int height) throws ImageSizeRatioException {
        if (width * HEIGHT_RATIO != height * WIDTH_RATIO) {
            throw new ImageSizeRatioException(
                "가로 세로 비율은 " + WIDTH_RATIO + " : " + HEIGHT_RATIO + "입니다");
        }
    }
}
