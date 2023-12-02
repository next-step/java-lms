package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageSizeException;

public class Height {

    private static final int MINIMUM = 200;

    private int px;

    public Height(int px) throws ImageSizeException {
        validate(px);
        this.px = px;
    }

    private void validate(int px) throws ImageSizeException {
        if (px < MINIMUM) {
            throw new ImageSizeException(String.format("이미지의 높이는 %s이상 이어야 합니다. 현재 높이 :: %spx", MINIMUM, px));
        }
    }
}
