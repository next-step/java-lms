package nextstep.courses.domain.vo;

import nextstep.courses.code.ImageType;

public class ImageSpec {

    private final int MAX_SIZE = 1024;
    private final int MIN_WIDTH = 300;
    private final int MIN_HEIGHT = 200;

    private int size;

    private int width;

    private int height;

    private ImageType imageType;


    public ImageSpec(int size,
                     int width,
                     int height,
                     String extension) {
        this(size, width, height, ImageType.of(extension));
    }

    public ImageSpec(int size,
                     int width,
                     int height,
                     ImageType imageType) {
        validateSize(size);
        validateWidthAndHeight(width, height);

        this.size = size;
        this.width = width;
        this.height = height;
        this.imageType = imageType;
    }

    private void validateSize(int size) {
        if (size > MAX_SIZE) {
            throw new IllegalArgumentException("이미지 크기는 1MB 이하이어야 합니다.");
        }
    }

    private void validateWidthAndHeight(int width, int height) {
        if (width < MIN_WIDTH) {
            throw new IllegalArgumentException("이미지 가로길이는 300픽셀 이상이어야 합니다.");
        }
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException("이미지 세로길이는 200픽셀 이상이어야 합니다.");
        }
        if (3 * height != 2 * width) {
            throw new IllegalArgumentException("가로 세로 비율이 3:2를 만족해야 합니다.");
        }
    }
}
