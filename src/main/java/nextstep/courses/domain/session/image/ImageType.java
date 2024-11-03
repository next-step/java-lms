package nextstep.courses.domain.session.image;

import nextstep.courses.ImageTypeMismatchException;

public enum ImageType {

    gif,
    jpg,
    jpeg,
    png,
    svg;

    public static final String IMAGE_TYPE_MISMATCH_MESSAGE = "이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.";

    public static ImageType toImageType(String name) {
        try {
            return ImageType.valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new ImageTypeMismatchException(IMAGE_TYPE_MISMATCH_MESSAGE);
        }
    }
}