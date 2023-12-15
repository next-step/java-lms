package nextstep.images.domain;

import java.util.EnumSet;
import java.util.Objects;

public class Image {

    private Long id;

    private ImageType imageType;

    private int size;

    private int width;

    private int height;

    public Image(Long id, ImageType imageType, int size, int width, int height) {
        checkImageSize(size);
        checkImageType(imageType);
        checkImageWidthAndHeight(width, height);
        this.id = id;
        this.imageType = imageType;
        this.size = size;
        this.width = width;
        this.height = height;
    }

    private void checkImageSize(int size) {
        if (size == 0) {
            throw new IllegalArgumentException("강의는 강의 커버 이미지 정보가 필요합니다.");
        }

        if (size > 1024 * 1024) { // 1MB
            throw new IllegalArgumentException("이미지 크기는 1MB 이하여야 합니다.");
        }
    }

    private void checkImageType(ImageType imageType) {
        if (Objects.isNull(imageType) || !EnumSet.allOf(ImageType.class).contains(imageType)) {
            throw new IllegalArgumentException("이미지 타입은 gif, jpg, jpeg, png, svg 만 가능합니다.");
        }
    }

    private void checkImageWidthAndHeight(int width, int height) {
        if (width == 0 || height == 0) {
            throw new IllegalArgumentException("강의는 강의 커버 이미지 정보가 필요합니다.");
        }

        if (width < 300 || height < 200) {
            throw new IllegalArgumentException("이미지 가로는 300 픽셀, 세로는 200 픽셀 이상이어야 합니다.");
        }

        if (2 * width != 3 * height) {
            throw new IllegalArgumentException("가로와 세로의 비율은 3:2여야 합니다");
        }
    }
}
