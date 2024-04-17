package nextstep.courses.domain;

import java.util.Objects;

public class CoverImage {

    private static final int IMAGE_SIZE_LIMIT_BYTE = 1024000000;
    private static final int MIN_WIDTH_PIXEL = 300;
    private static final int MIN_HEIGHT_PIXEL = 200;
    private static final double IMAGE_RAtIO = (double) 3 / 2;

    private int imageSize;

    private ImgaeType imageType;

    private int width;

    private int height;

    public CoverImage(int imageSize) {
        this.imageSize = checkImageSize(imageSize);
    }

    public CoverImage(ImgaeType imageType) {
        this.imageType = imageType;
    }

    public CoverImage(int width, int height) {
        this.width = checkWidth(width);
        this.height = checkHeight(height);
        if ((double) this.width /this.height != IMAGE_RAtIO) {
            throw new IllegalArgumentException("이미지 비율이 맞지 않습니다.");
        }
    }

    private int checkImageSize(int imageSize) {
        if (imageSize >= IMAGE_SIZE_LIMIT_BYTE) {
            throw new IllegalArgumentException("이미지 최대 크기 1MB 초과 합니다.");
        }
        return imageSize;
    }

    private int checkWidth(int width) {
        if (width < MIN_WIDTH_PIXEL) {
            throw new IllegalArgumentException("이미지 사이즈가 작습니다.");
        }
        return width;
    }

    private int checkHeight(int height) {
        if (height < MIN_HEIGHT_PIXEL) {
            throw new IllegalArgumentException("이미지 사이즈가 작습니다.");
        }
        return height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoverImage that = (CoverImage) o;
        return imageSize == that.imageSize && width == that.width && height == that.height && imageType == that.imageType;
    }

}
