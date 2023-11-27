package nextstep.courses.domain;

public class Image {

    public static final int MAXIMUM_SIZE = 1024 * 1024;
    public static final int MINIMUM_WIDTH = 300;
    public static final int MINIMUM_HEIGHT = 200;
    public static final int RATE_BETWEEN_WIDTH_AND_HEIGHT = 3 / 2;
    private final String path;
    private final int width;
    private final int height;
    private final ImageType imageType;

    public Image(String path, int width, int height, ImageType imageType) {

        inputValidation(width, height, imageType);
        this.path = path;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void inputValidation(int width, int height, ImageType imageType) {
        if (width * height > MAXIMUM_SIZE) {
            throw new IllegalArgumentException(String.format("이미지 크기는 1MB 이하여야 합니다. 현재 사이즈 : %d", width * height));
        }

        if(width < MINIMUM_WIDTH){
            throw new IllegalArgumentException(String.format("이미지의 가로는 300px 이상이어야 합니다. 현재 가로 : %d", width));
        }

        if(height < MINIMUM_HEIGHT){
            throw new IllegalArgumentException(String.format("이미지의 세로는 200px 이상이어야 합니다. 현재 세로 : %d", height));
        }

        if(width/height != RATE_BETWEEN_WIDTH_AND_HEIGHT){
            throw new IllegalArgumentException(String.format("이미지의 가로 세로 비율은 3:2 여야 합니다. 현재 비율 : %d:%d", width, height));
        }

    }

}
