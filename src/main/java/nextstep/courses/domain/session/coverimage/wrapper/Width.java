package nextstep.courses.domain.session.coverimage.wrapper;

import nextstep.courses.exception.ImageSizeException;

public class Width {

    public static final int MINIMUM = 300;

    private int value;

    public Width(int value) throws ImageSizeException {
        validate(value);
        this.value = value;
    }

    private void validate(int value) throws ImageSizeException {
        if (value < MINIMUM) {
            throw new ImageSizeException(String.format("이미지의 너비는 %s이상 이어야 합니다. 현재 너비 :: %spx", MINIMUM, value));
        }
    }
}
