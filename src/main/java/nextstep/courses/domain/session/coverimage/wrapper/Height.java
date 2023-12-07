package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageFileInfoException;

public class Height {

    private static final int MINIMUM = 200;

    private int px;

    public Height(int px) throws ImageFileInfoException {
        validate(px);
        this.px = px;
    }

    private void validate(int px) throws ImageFileInfoException {
        if (px < MINIMUM) {
            throw new ImageFileInfoException(String.format("이미지의 높이는 %s이상 이어야 합니다. 현재 높이 :: %spx", MINIMUM, px));
        }
    }
}
