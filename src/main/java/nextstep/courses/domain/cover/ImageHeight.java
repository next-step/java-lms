package nextstep.courses.domain.cover;

import nextstep.courses.error.exception.ImageHeightSizeException;

public class ImageHeight {

    private final int value;

    public ImageHeight(int value) {
        if(value < 200){
            throw new ImageHeightSizeException(value);
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
