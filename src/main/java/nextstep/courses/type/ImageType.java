package nextstep.courses.type;

import nextstep.courses.exception.image.UnsupportedImageExtensionException;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static ImageType from(String value) {
        try {
            return ImageType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new UnsupportedImageExtensionException("지원하지 않는 이미지 확장자입니다.", e);
        }
    }
}
