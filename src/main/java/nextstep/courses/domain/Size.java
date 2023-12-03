package nextstep.courses.domain;

public class Size {
    public static final int MAXIMUM_WIDTH = 1024;
    public static final int MAXIMUM_HEIGHT = 1024;
    public static final int MAXIMUM_SIZE = MAXIMUM_WIDTH * MAXIMUM_HEIGHT;
    public static final int MINIMUM_WIDTH = 300;
    public static final int MINIMUM_HEIGHT = 200;
    public static final int RATE_OF_WIDTH = 3;
    public static final int RATE_OF_HEIGHT = 2;
    public static final int RATE_BETWEEN_WIDTH_AND_HEIGHT = RATE_OF_WIDTH / RATE_OF_HEIGHT;
    public static final String RATE_STRING_BETWEEN_WIDTH_AND_HEIGHT = "3:2";
    public static final String MESSAGE_INVALID_IMAGE_SIZE = "이미지 크기는 %d 이하여야 합니다. 현재 사이즈 : %d";
    public static final String MESSAGE_INVALID_IMAGE_WIDTH = "이미지의 가로는 %dpx 이상이어야 합니다. 현재 가로 : %d";
    public static final String MESSAGE_INVALID_IMAGE_HEIGHT = "이미지의 세로는 %d 이상이어야 합니다. 현재 세로 : %d";
    private final int width;
    private final int height;


    public Size(int width, int height) {
        inputValidation(width, height);
        this.width = width;
        this.height = height;
    }

    public int size() {
        return width * height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    private void inputValidation(int width, int height) {
        if (width * height > MAXIMUM_SIZE) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_IMAGE_SIZE, MAXIMUM_SIZE, width * height));
        }

        if(width < MINIMUM_WIDTH){
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_IMAGE_WIDTH, MINIMUM_WIDTH, width));
        }

        if(height < MINIMUM_HEIGHT){
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_IMAGE_HEIGHT, MINIMUM_HEIGHT, height));
        }

        if(width/height != RATE_BETWEEN_WIDTH_AND_HEIGHT){
            throw new IllegalArgumentException(String.format("이미지의 가로 세로 비율은 %s 여야 합니다. 현재 비율 : %d:%d",RATE_STRING_BETWEEN_WIDTH_AND_HEIGHT, width, height));
        }

    }
}
