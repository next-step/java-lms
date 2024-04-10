package nextstep.courses.domain.cover;

import nextstep.courses.error.exception.ImageSizeException;

public class ImageSize {

    private final int value;

    public ImageSize(int value) {
        if(value > 1){
            throw new ImageSizeException(value);
        }

        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
