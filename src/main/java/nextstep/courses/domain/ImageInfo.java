package nextstep.courses.domain;

import nextstep.courses.exception.ImageException;

import java.util.Objects;

public class ImageInfo {
    private static final Long MAXIMUM_FILE_SIZE_MB = 1L;
    private static final int MINIMUM_WIDTH_PX = 300;
    private static final int MINIMUM_HEIGHT_PX = 200;

    private ImageType type;

    private Long size;

    private int width;

    private int height;

    public ImageInfo(String type, Long size, int width, int height) {
        isSupportFileSize(size);
        isSupportImageSize(width, height);
        ImageType.isSupportImageType(type);

        this.type = ImageType.valueOf(type);
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private void isSupportFileSize(Long size) {
        if (size > MAXIMUM_FILE_SIZE_MB) {
            throw new ImageException("이미지 크기가 1MB를 초과하였습니다.");
        }
    }

    private void isSupportImageSize(int width, int height) {
        if(width < MINIMUM_WIDTH_PX || height < MINIMUM_HEIGHT_PX) {
            throw new ImageException("이미지의 가로 길이는 300픽셀, 세로 길이는 200픽셀 이상이어야 합니다.");
        }

        if(isSupportImageRatio(width, height)) {
            throw new ImageException("이미지의 가로와 세로의 비율이 3:2가 아닙니다.");
        }
    }

    private boolean isSupportImageRatio(int width, int height) {
        return width * 2 != height * 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageInfo imageInfo = (ImageInfo) o;
        return width == imageInfo.width && height == imageInfo.height && type == imageInfo.type && Objects.equals(size, imageInfo.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, size, width, height);
    }

    @Override
    public String toString() {
        return "CoverImage{" +
                "type='" + type + '\'' +
                ", size=" + size +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

}