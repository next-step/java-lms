package nextstep.courses.domain;

import java.util.List;

public enum ImageType {
    GIF,
    JPG,
    JPEG,
    PNG,
    SVG;

    public static boolean isSupportImageType(String imageType) {
        return List.of(ImageType.values()).contains(ImageType.valueOf(imageType.toUpperCase()));
    }


}
