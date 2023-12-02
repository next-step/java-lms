package nextstep.courses.domain.session.coverimage;

import nextstep.courses.domain.session.coverimage.wrapper.Height;
import nextstep.courses.domain.session.coverimage.wrapper.Width;
import nextstep.courses.exception.ImageSizeException;

public class Size {

    private static final int HORIZONTAL_RAITO = 3;
    private static final int VERTICAL_RAITO = 2;

    private Width width;
    private Height height;

    public Size(int width, int height) throws ImageSizeException {
        validateAspectRatio(width, height);
        this.width = new Width(width);
        this.height = new Height(height);
    }

    private void validateAspectRatio(int width, int height) throws ImageSizeException {
        double roundsRatio = calculateRatio(width, height);

        if (roundsRatio != VERTICAL_RAITO) {
            throw new ImageSizeException(String.format("이미지의 가로-세로 비율은 3:2 이어야 합니다. 현재 비율 :: 3:%s", roundsRatio));
        }
    }

    private static double calculateRatio(int width, int height) {
        double verticalRatio = (double) (height * HORIZONTAL_RAITO) / width;
        return Math.round(verticalRatio * 1000) / 1000.0;
    }
}
