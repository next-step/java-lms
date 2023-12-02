package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageSizeException;

public class Width {

    private static final int MINIMUM = 300;

    private int px;

    public Width(int px) throws ImageSizeException {
        validate(px);
        this.px = px;
    }

    private void validate(int px) throws ImageSizeException {
        if (px < MINIMUM) {
            throw new ImageSizeException(String.format("이미지의 너비는 %s이상 이어야 합니다. 현재 너비 :: %spx", MINIMUM, px));
        }
    }
}
