package nextstep.courses.domain.session;

import nextstep.courses.domain.session.constant.ImageTypeEnum;

public class Image {
    private final int MAXIMUM_WIDTH = 300;
    private final int MAXIMUM_HEIGHT = 200;
    private int size;
    private ImageTypeEnum type;
    private int width;
    private int height;

    public Image(int size, String type, int width, int height) {
        this.size = checkValidSize(size);
        this.type = ImageTypeEnum.findByType(type);
        checkValidWidthAndHeight(width, height);
        this.width = width;
        this.height = height;
    }

    private int checkValidSize(int size) {
        if (1 < size) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다. 더 작은 이미지 크기를 입력해주세요");
        }
        return size;
    }

    private void checkValidWidthAndHeight(int width, int height) {
        if (width < MAXIMUM_WIDTH) {
            throw new IllegalArgumentException("이미지의 너비는 최소 300 이상이어야 합니다.");
        } else if (height < MAXIMUM_HEIGHT) {
            throw new IllegalArgumentException("이미지의 너비는 최소 300 이상이어야 합니다.");
        }
        if (width * 2 != height * 3) {
            throw new IllegalArgumentException("이미지의 너비와 높이의 비율은 3:2이어야 합니다.");
        }
    }
}
