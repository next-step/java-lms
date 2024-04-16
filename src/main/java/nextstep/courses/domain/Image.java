package nextstep.courses.domain;

import java.util.Objects;

public class Image {
    private final int size;
    private final int MB = 1024 * 1024;
    private final int MINIMUM_WIDTH = 300;
    private final int MINIMUM_HEIGHT = 200;
    private final ImageType imageType;
    private final int width;
    private final int height;

    public Image(int size, ImageType type, int width, int height) {
        imagePixel(width, height);
        imageSize(size);
        imageType(type);
        this.size = size;
        this.imageType = type;
        this.width = width;
        this.height = height;
    }

    private void imageSize(int size) {
        if (size > 1 * MB)
            throw new IllegalArgumentException("이미지 파일 크기가 너무 큽니다");
    }
    private void imagePixel(int width, int height) {
        if (width < MINIMUM_WIDTH || height < MINIMUM_HEIGHT)
            throw new IllegalArgumentException("크기가 맞지 않습니다");
        if (!(width * 2 == height * 3))
            throw new IllegalArgumentException("비율이 맞지 않습니다");
    }

    private void imageType(ImageType type) {
        if (type == null)
            throw new IllegalArgumentException("잘못된 파일 타입입니다");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return size == image.size && width == image.width && height == image.height && imageType == image.imageType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, MB, imageType, width, height);
    }

    @Override
    public String toString() {
        return "Image{" +
                "size=" + size +
                ", MB=" + MB +
                ", type=" + imageType +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
