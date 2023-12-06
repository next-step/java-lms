package nextstep.courses.domain;

import nextstep.courses.exception.ImageSizeException;

public class ImageInfo {
    private static final Long IMAGE_SIZE_LIMIT_MB = 1L;

    private ImageType type;

    private Long size;

    private int width;

    private int height;

    public ImageInfo(ImageType type, Long size, int width, int height) {
        if(!isSupportImageSize(size)){
            throw new ImageSizeException("이미지 크기가 1MB를 초과하였습니다.");
        }
        this.type = type;
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private boolean isSupportImageSize(Long size) {
        return size <= IMAGE_SIZE_LIMIT_MB;
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
