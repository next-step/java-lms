package nextstep.courses.domain.cover;

import nextstep.courses.error.exception.ImageWidthSizeException;

public class ImageWidth {

    private final int value;

    public ImageWidth(int value) {
        if (value < 300){
            throw new ImageWidthSizeException(value);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

